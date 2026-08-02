package dev.harold.retrofitpath.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import dev.harold.retrofitpath.R
import dev.harold.retrofitpath.model.AmphibianInfo

@Composable
fun AmphibianScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    ) {
    when(amphibianUiState) {
        is AmphibianUiState.Success -> {
            AmphibianList(
                amphibians = amphibianUiState.amphibians,
                modifier = modifier,
                contentPadding = contentPadding
            )
        }
        is AmphibianUiState.Loading -> {
            LoadingScreen(modifier = modifier)
        }
        is AmphibianUiState.Error -> {
            ErrorScreen(
                retryAction = retryAction,
                modifier = modifier
            )
        }
    }
}

@Composable
fun AmphibianList(
    amphibians: List<AmphibianInfo>,
    modifier: Modifier,
    contentPadding: PaddingValues
) {
    LazyColumn(contentPadding = contentPadding) {
        items(amphibians) { amphibian ->
            AmphibianCard(
                amphibian = amphibian,
                modifier = modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun AmphibianCard(amphibian: AmphibianInfo, modifier: Modifier) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = amphibian.name)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.amphibian_image),
                contentScale = ContentScale.Fit,

                modifier = Modifier.fillMaxWidth(),
            )
            Text(text = amphibian.type)
            Text(text = amphibian.description)
        }
    }
}

