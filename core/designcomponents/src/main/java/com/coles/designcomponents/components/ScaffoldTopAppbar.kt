package com.coles.designcomponents.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppbar(
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    title: String,
    onNavigationIconClick: () -> Unit,
    navigationIcon: Painter = rememberVectorPainter(image = Icons.Outlined.ArrowBack),
    snackbarHost: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
        contentColor = contentColor,
        snackbarHost = snackbarHost,
        topBar = {
            Surface(shadowElevation = 1.dp) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = title)
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                    navigationIcon = {
                        IconButton(onClick = onNavigationIconClick) {
                            Icon(
                                painter = navigationIcon,
                                contentDescription = "navigationIcon"
                            )
                        }
                    },
                )
            }
        },
        bottomBar = bottomBar,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppbar(
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    title: String,
    snackbarHost: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        contentColor = contentColor,
        topBar = {
            Surface(
                shadowElevation = 2.dp
            ) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = title)
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    )
                )
            }
        },
        bottomBar = bottomBar,
        content = content
    )
}


