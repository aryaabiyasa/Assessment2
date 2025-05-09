package com.aryama0073.kabarharian.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aryama0073.kabarharian.R
import com.aryama0073.kabarharian.model.Catatan
import com.aryama0073.kabarharian.navigation.Screen
import com.aryama0073.kabarharian.ui.theme.KabarHarianTheme
import com.aryama0073.kabarharian.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecycleScreen(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary

                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.halaman_recycle))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        RecycleContent( Modifier.padding(innerPadding), navController)
    }
    if (showDialog) {
        DisplayAlertDialog(
            onDismissRequest = { showDialog = false }
        ) {
            showDialog = false
        }
    }
}

@Composable
fun RecycleContent(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.dataRecycle.collectAsState()

    if (data.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.recycle_kosong))
        }
    }
    else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp)
        ) {
            items(data) {
                RecycleListItem(catatan = it) {
                    navController.navigate(Screen.FormUbah.withId(it.id))
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun RecycleListItem(catatan: Catatan, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = catatan.nama,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(text = catatan.kabar)
        Text(
            text = catatan.catatan,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = catatan.tanggal)
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecyclePreview() {
    KabarHarianTheme {
        RecycleScreen(rememberNavController())
    }
}