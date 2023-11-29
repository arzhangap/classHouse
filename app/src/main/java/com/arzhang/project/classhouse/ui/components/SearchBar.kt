package com.arzhang.project.classhouse.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCard(
    inputValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(value = inputValue,
        onValueChange = {onValueChange(it)},
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background) ,
        shape = RoundedCornerShape(50.dp),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp))
}
