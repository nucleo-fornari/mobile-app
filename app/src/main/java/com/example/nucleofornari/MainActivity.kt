package com.example.nucleofornari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nucleofornari.ui.theme.NucleoFornariTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NucleoFornariTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /*
   Sempre que algum valor da tela sofre qualquer mudança, toda a tela é renderizada novamente. Chamamos isso de RECOMPOSIÇÃO

   Um objeto "remember" sobrevive às recomposições
   aqui definimos um remember cujo valor inicial é um texto em branco
    */
    var valorA by remember { mutableStateOf("") }
    var valorB by remember { mutableStateOf("") }
    var exibirBotao by remember { mutableStateOf (true) }
    var valorCpf by remember { mutableStateOf("") }
    var textoSwitch by remember { mutableStateOf("") }
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }


    // Uma Column é um componente no qual seus filhos sempre ficam um debaixo do outro
    // padding aqui é como no CSS
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = Color(0xFF1976D2))) {

        // Text é um texto simples na tela
        Text(
            text = "É nóis no Android!",
            modifier = modifier
                .padding(bottom = 20.dp) // unidade dp, para distâncias e desenhos
                .background(Color.Cyan)
                .fillMaxWidth() // indica que ocupará toda a largura possível
        )
        Text(
            text = "Minha segunda linha Android",
            modifier = modifier,
            style = TextStyle(
                fontSize = 25.sp, // unidade sp, somente para tamanho de texto
                color = Color(
                    red = 66,
                    green = 89,
                    blue = 70,
                    alpha = 255
                ),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        )

        // O Spacer serve apenas para criar um espaço entre componentes
        Spacer(modifier = Modifier.height(30.dp))

        /*
        Aqui o texto do Text é o valor do remember valorA.
        Logo, se algum componente alterar o valorA, o texto aqui altera imediatamente.
        Por isso que ao digitar um valor no TextField, o valor aqui muda junto, pois ambos compartilham o mesmo remember
         */
        Text(text = valorA)

        // esse teste lógico é refeito em toda recomposição
        if (valorA.length > 7) {
            // este componente só existirá na tela se o teste do if for true
            Text(text = "Que texto grande!!!", color = Color.DarkGray)
        }

        // Botão simples. O "onClick" é obrigatório, mesmo que esteja vazio
        if (exibirBotao){
            Button(onClick = {  }) {
                // é necessário um Text (ou outro componente visual) dentro do Button, senão o botão fica "vazio"
                Text(
                    text = "Clica nimin",
                    fontSize = 30.sp
                )
            }
        }


        /*
        Campo de entrada de texto.
        O value normalmente é associado a um objeto remember
        O onValueChange indica o que acontece quando se digita nele. O it dentro dele é o valor logo após a alteração
        O label é o texto indicativo do campo
         */
        TextField(
            value = valorA,
            onValueChange = { valorA = it },
            label = { Text("Campo A") }
        )

        /*
        Semelhante ao TextField, só que mais "moderno e bonito"
         */
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Campo B") }
        )
        OutlinedTextField(
            value = valorCpf,
            onValueChange = { valorCpf = it},
            label = { Text("CPF completo") },
            supportingText = {
                if (valorCpf.isNotBlank() && valorCpf.length < 11) {
                    Text("Digite o CPF completo", color = Color.Red)}
            },
        )
        Row {
            if (exibirBotao === false){
                textoSwitch = "Exibir botão 😒"
            }else{
                textoSwitch = "Botão sendo exibido \uD83D\uDE0D"
            }
            Text(
                text = textoSwitch,
                modifier = Modifier.padding(top = 12.dp, end = 10.dp)
            )
            Switch(
                checked = exibirBotao,
                onCheckedChange = { exibirBotao = it }
            )
        }
        Row (horizontalArrangement = Arrangement.spacedBy(20.dp)){
            OutlinedTextField(
                value = numero1,
                onValueChange = { numero1 = it },
                label = { Text("Número 1") },
                modifier = Modifier.weight(0.5f)
            )
            OutlinedTextField(
                value = numero2,
                onValueChange = { numero2 = it },
                label = { Text("Número 1") },
                modifier = Modifier.weight(0.5f)
            )
        }
        if (numero1.isNotBlank() && numero2.isNotBlank()){
            val soma = numero1.toDouble() + numero2.toDouble()

            Text(
                text = "A soma é: $soma",
                fontSize = 30.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NucleoFornariTheme {
        Greeting("Android")
    }
}