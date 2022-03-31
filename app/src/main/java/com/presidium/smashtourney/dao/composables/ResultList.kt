package com.presidium.smashtourney.dao.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.presidium.smashtourney.R
import com.presidium.smashtourney.dao.searchResults.ResultType
import com.presidium.smashtourney.dao.searchResults.SearchResult
import com.presidium.smashtourney.dao.title.Title

class ResultList {
    
    
    @Composable
    fun ResultList(searchResults: Array<SearchResult>, onNavigate: (Int) -> (Unit)) {
        LazyColumn {
            // Add a single item as a header
            item {
                Text("Search Results")
            }
            
            // Add list of messages
            items(searchResults){ item: SearchResult ->
                ResultCard(searchResult = item, onNavigate = onNavigate)
            }
        }
    }
    
    @Composable
    fun ResultCard(searchResult: SearchResult, onNavigate: (Int) -> Unit) {// Add padding around our message
        Row(
                Modifier
                    .clickable {
                        onNavigate(R.id.nav_graph)
                    }
                    .padding(all = 8.dp)
            ) {
            Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        // Set image size to 40 dp
                        .size(40.dp)
                        // Clip image to be shaped as a circle
                        .clip(CircleShape)
            )
            
            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))
            
            Column {
                Text(text = searchResult.name!!)
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = searchResult.id!!)
            }
        }
    }
    
//    @Preview
//    @Composable
//    fun PreviewMessageCard() {
//        val results = arrayOf(SearchResult("1", ResultType.EVENT, "Abate" ), SearchResult("2", ResultType.EVENT, "Plup" ) )
//        MaterialTheme {
//            ResultList(
//                    results,
//            )
//        }
//    }
}