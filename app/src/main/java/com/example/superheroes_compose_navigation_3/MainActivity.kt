package com.example.superheroes_compose_navigation_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.data.models.heroes.Result
import com.example.superheroes_compose_navigation_3.features.Search.SearchHeroesViewModel
import com.example.superheroes_compose_navigation_3.features.details.HeroViewModel
import com.example.superheroes_compose_navigation_3.ui.theme.SuperheroesComposeNavigation3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesComposeNavigation3Theme {
                MainScaffold()
            }
        }
    }
}

@PreviewLightDark
@Composable
fun GreetingPreview() {
    SuperheroesComposeNavigation3Theme {
        MainScaffold()
    }
}

sealed class Screens {
    data object Home : Screens()
    data class Details(val heroId: String) : Screens()
}

@Composable
fun MainNavigation() {
    val backStack = remember { mutableStateListOf<Screens>(Screens.Home) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {
                is Screens.Home -> NavEntry(route) { HeroesSearchView(){ id ->
                    backStack.add(Screens.Details(heroId = id))
                } }
                is Screens.Details -> NavEntry(route) { HeroDetailsScreen(route.heroId) }
                else -> NavEntry(route) {
                    Text("Unknown")
                }
            }
        }
    )
}

@Composable
fun MainScaffold() {
    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MainNavigation()
        }
    }
}

@PreviewLightDark
@Composable
fun MainScreenPreview() {
    SuperheroesComposeNavigation3Theme {
        HeroesSearchView(){
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesSearchView(
    onHeroClick: (String) -> Unit
) {
    val viewModel: SearchHeroesViewModel = hiltViewModel()
    val heroes by viewModel.heroes.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var searchQuery by remember { mutableStateOf("") }
        SearchTextField(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { viewModel.searchHeroes(searchQuery) }
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(heroes ?: emptyList(), key = { it.id }) { hero ->
                HeroCard(hero) {
                    onHeroClick(hero.id)
                }
            }
        }
    }
}

@Composable
fun HeroDetailsScreen(heroId: String) {
    val viewModel: HeroViewModel = hiltViewModel(key = heroId)
    LaunchedEffect(Unit) {
        viewModel.getHero(heroId)
    }
    val hero by viewModel.hero.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(hero?.image?.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "${hero?.name} image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(200.dp),
        )

        if(hero?.name?.isNotEmpty() == true) {
            Text(
                text = hero?.name ?: "",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
        // Other hero details
    }
}


@Composable
fun HeroCard(
    hero: Result,
    modifier: Modifier = Modifier,
    onHeroClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{ onHeroClick(hero.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.image.url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "${hero.name} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(44.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Hero Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = hero.name,
                    style = MaterialTheme.typography.titleMedium
                )
                if (hero.connections.groupAffiliation.isNotBlank()) {
                    Text(
                        text = hero.connections.groupAffiliation,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2
                    )
                }
            }
        }
    }
}