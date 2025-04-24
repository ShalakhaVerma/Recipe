package com.coles.feature.recipes.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.coles.designcomponents.components.ImagePlaceHolder
import com.coles.entity.RecipeItemEntity

@Composable
fun RecipeColumn(item: RecipeItemEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            ImagePlaceHolder(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)  // Ensure consistent aspect ratio
                ,
                item.url
            )

            Spacer(modifier = Modifier.height(8.dp))
            item.title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            item.desc?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}