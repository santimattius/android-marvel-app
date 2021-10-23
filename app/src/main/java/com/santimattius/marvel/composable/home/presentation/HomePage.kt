package com.santimattius.marvel.composable.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.santimattius.marvel.composable.R
import com.santimattius.marvel.composable.home.application.HomeViewModel
import com.santimattius.marvel.composable.home.domain.Character
import com.santimattius.marvel.composable.home.domain.Characters
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(onClick: (Character) -> Unit) {
    val viewModel: HomeViewModel = getViewModel()
    CharactersScreen(viewModel.characters, onClick)
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(data: Flow<Characters>, onClick: (Character) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { padding ->
        val characters: LazyPagingItems<Character> = data.collectAsLazyPagingItems()
        val swipeRefreshState =
            rememberSwipeRefreshState(isRefreshing = characters.loadState.refresh == LoadState.Loading)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { characters.refresh() },
        ) {
            GridOfCharacters(characters, padding, onClick)
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
private fun GridOfCharacters(
    characters: LazyPagingItems<Character>,
    padding: PaddingValues,
    onClick: (Character) -> Unit
) {

    LazyVerticalGrid(
        cells = GridCells.Adaptive(dimensionResource(R.dimen.item_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.x_small)),
        modifier = Modifier.padding(padding)
    ) {

        items(characters.itemCount) { index ->
            val character = characters[index] ?: return@items
            CharacterItem(
                character = character,
                modifier = Modifier.clickable { onClick(character) }
            )
        }

        if (characters.loadState.append == LoadState.Loading) {
            items(2) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
    if (characters.loadState.refresh == LoadState.Loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.small))
    ) {
        Card {
            Image(
                painter = rememberImagePainter(data = character.thumbnail),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(ratio = 0.67f),
            )
        }
        Box(
            modifier = Modifier.padding(
                dimensionResource(R.dimen.small),
                dimensionResource(R.dimen.medium)
            )
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2
            )
        }
    }
}