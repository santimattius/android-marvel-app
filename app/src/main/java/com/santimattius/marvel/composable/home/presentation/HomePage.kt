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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
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
    val characters = data.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            cells = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(characters.itemCount) { index ->
                val character = characters[index] ?: return@items
                CharacterItem(
                    character = character,
                    modifier = Modifier.clickable { onClick(character) }
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            Image(
                painter = rememberImagePainter(data = character.thumbnail),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f)
            )
        }
        Box(
            modifier = Modifier.padding(8.dp, 16.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2
            )
        }
    }
}