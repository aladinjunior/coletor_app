package com.aladinjunior.coletor.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aladinjunior.coletor.R
import com.aladinjunior.coletor.camera.CameraPreview
import com.aladinjunior.coletor.util.Constants.ACTIONS
import com.aladinjunior.coletor.util.Constants.APP_HEADLINE
import com.aladinjunior.coletor.util.Constants.GENERATE_FILE
import com.aladinjunior.coletor.util.Constants.INSTRUCTION_TEXT
import com.aladinjunior.coletor.util.Constants.SCANNED_BARCODE
import com.aladinjunior.coletor.util.Constants.START_COLLECT
import com.aladinjunior.coletor.util.Constants.assistChipList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(
    mostRecentBarcode: (String?) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

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
            CameraPreview { barcode ->
                barcode.let {
                    showBottomSheet = true
                }
            }
        }

        Button(
            onClick = { /*TODO*/ },
            shape = AssistChipDefaults.shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue.copy(alpha = 0.6f)
            )
        ) {
            Icon(imageVector = Icons.Filled.Create, contentDescription = "Insert code")
            Spacer(modifier = Modifier.size(8.dp))
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
        if (showBottomSheet) {
            ColetorBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            )
        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColetorBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            text = SCANNED_BARCODE,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        BottomSheetBarcodeHeadline()

        ColetorTextField()

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue.copy(alpha = 0.6f)
            ),
            shape = RoundedCornerShape(16f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
            Text(text = "Inserir")
        }
    }
}

@Composable
fun BottomSheetBarcodeHeadline() {
    Surface(
        modifier = Modifier.size(width = 400.dp, height = 200.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(14f)
            ) {
                val styledBarcodeText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 18.sp
                        )
                    ) {
                        append("Código de Barras: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Blue.copy(alpha = 0.6f)
                        )
                    ) {
                        append("000000000")
                    }

                }
                Text(text = styledBarcodeText, modifier = Modifier.padding(18.dp))
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BarcodeBottomSheetPreview() {
    ColetorBottomSheet(onDismissRequest = { /*TODO*/ }, sheetState = rememberModalBottomSheetState())

}

@Composable
fun FakeCameraPreview(
    fakeMostRecentBarcode: (String?) -> Unit
) {
    fakeMostRecentBarcode("hello world!")
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
                    when (action) {
                        START_COLLECT -> Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Start collect",
                            modifier = Modifier.size(AssistChipDefaults.IconSize),
                            tint = Color.Blue.copy(alpha = 0.6f)
                        )

                        GENERATE_FILE -> Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Generate file",
                            modifier = Modifier.size(AssistChipDefaults.IconSize),
                            tint = Color.Blue.copy(alpha = 0.6f)
                        )
                    }

                }
            )
        }


    }
}

@Composable
fun ColetorTextField() {
    var textState by remember { mutableStateOf("") }

    TextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        value = textState,
        onValueChange = { textState = it },
        label = { Text(text = "Quantidade")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(14f)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            focusedBorderColor = Color.Blue.copy(alpha = 0.6f),
            unfocusedBorderColor = Color.LightGray,
        )
    )
}

@Preview
@Composable
private fun ColetorTextFieldPreview() {
    ColetorTextField()
}

