package com.aladinjunior.coletor.main

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aladinjunior.coletor.util.Constants.ACTIONS
import com.aladinjunior.coletor.util.Constants.APP_HEADLINE
import com.aladinjunior.coletor.util.Constants.GENERATE_FILE
import com.aladinjunior.coletor.util.Constants.INSTRUCTION_TEXT
import com.aladinjunior.coletor.util.Constants.START_COLLECT
import com.aladinjunior.coletor.util.Constants.assistChipList
import com.google.mlkit.vision.barcode.common.Barcode


@Composable
fun ScanScreen(
    detectedBarcodesCallback: (List<Barcode>) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = APP_HEADLINE,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = INSTRUCTION_TEXT,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            FakeCameraPreview()
        }

        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(15f)
        ) {
            Icon(imageVector = Icons.Filled.Create, contentDescription = "Insert code")
            Text(text = "Digite o código de barras")
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            text = ACTIONS,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        AssistChipRow()


    }


}

@Composable
fun FakeCameraPreview() {
    Box(
        modifier = Modifier
            .size(350.dp)
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black)
    ) {

    }
}

@Composable
fun AssistChipRow() {

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        assistChipList.forEach { action ->
            AssistChip(
                onClick = {

                },
                label = { Text(text = action) },
                leadingIcon = {
                    when(action) {
                        START_COLLECT -> Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Start collect",
                            modifier = Modifier.size(AssistChipDefaults.IconSize)
                        )
                        GENERATE_FILE -> Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Generate file",
                            modifier = Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }

                }
            )
        }


    }
}

@Preview
@Composable
private fun ScanScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),

        ) {

    }
    ScanScreen {

    }
}

