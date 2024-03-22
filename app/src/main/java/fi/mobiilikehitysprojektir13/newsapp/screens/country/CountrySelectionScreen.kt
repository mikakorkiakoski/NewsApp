package fi.mobiilikehitysprojektir13.newsapp.screens.country

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountrySelectionScreen () {

    Surface (
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Black

    ){

        Column(
            modifier = Modifier

        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                color = Color.DarkGray
            ) {
                Text(
                    text = "Select country",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            //Map goes here

            Row {

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Auto-detect location")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Use manually selected location")
                }

            }



        }
    }
}


@Preview
@Composable
fun CountrySelectionScreenPreview(){
 CountrySelectionScreen()


}