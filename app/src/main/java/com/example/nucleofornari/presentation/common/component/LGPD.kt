package com.example.nucleofornari.presentation.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun PoliticaLGPDText() {
    val bullet = "\u2022"
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(buildAnnotatedString {
            append("1. Introdução\n")
            append("A presente Política de Privacidade tem como objetivo informar aos usuários do Núcleo Fornari como os dados pessoais são coletados, utilizados, armazenados e protegidos em conformidade com a LGPD.")
        })

        Text(buildAnnotatedString {
            append("2. Dados Pessoais Coletados\n")
            append("$bullet Nome completo, e-mail e telefone para cadastro e comunicação\n")
            append("$bullet Dados de funcionários e professores (contato e funções)\n")
            append("$bullet Dados essenciais de alunos, como nome e contato dos responsáveis.")
        })

        Text(buildAnnotatedString {
            append("3. Finalidade da Coleta de Dados\n")
            append("$bullet Prover acesso ao sistema\n")
            append("$bullet Comunicação e notificações\n")
            append("$bullet Gestão administrativa escolar\n")
            append("$bullet Garantir a segurança e bom funcionamento da plataforma")
        })

        Text(buildAnnotatedString {
            append("4. Compartilhamento de Dados\n")
            append("$bullet Obrigação legal\n")
            append("$bullet Serviços essenciais com garantia de conformidade à LGPD")
        })

        Text("5. Armazenamento de Dados\nDados armazenados com segurança e por tempo necessário.")

        Text(buildAnnotatedString {
            append("6. Direitos dos Titulares\n")
            append("$bullet Confirmar existência de tratamento\n")
            append("$bullet Acessar e corrigir dados\n")
            append("$bullet Solicitar anonimização ou eliminação\n")
            append("$bullet Pedir portabilidade\n")
            append("$bullet Revogar consentimento")
        })

        Text("7. Segurança da Informação\nAdoção de criptografia, controle de acesso e auditorias.")
        Text("8. Alterações na Política\nAtualizações serão publicadas nesta página.")
        Text("9. Contato\nEm caso de dúvidas, entre em contato com a secretaria.")
    }
}
