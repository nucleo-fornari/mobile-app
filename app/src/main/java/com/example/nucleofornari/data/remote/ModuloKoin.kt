package com.example.nucleofornari.data.remote

import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.presentation.screen.auth.login.LoginViewModel
import com.example.nucleofornari.presentation.screen.auth.login.RecuperacaoSenhaViewModel
import com.example.nucleofornari.presentation.screen.responsavel.AgendaViewModel
import com.example.nucleofornari.presentation.screen.professor.CategoriasViewModel
import com.example.nucleofornari.presentation.screen.professor.ChamadosViewModel
import com.example.nucleofornari.presentation.screen.professor.InicioProfessorViewModel
import com.example.nucleofornari.presentation.screen.responsavel.PublicacoesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val moduloGeral = module {
    single<SessaoUsuario> {
        SessaoUsuario()
    }
}

val moduloApiReal = module {
    factory<UsuarioApiService> {
        UsuarioApi.getApi(get<SessaoUsuario>().token)
    }

    viewModel<LoginViewModel> {
        LoginViewModel(
            usuarioApi = get(),
            sessaoUsuario = get()
        )
    }

    viewModel<AgendaViewModel> { AgendaViewModel(get(), get()) }
    viewModel<PublicacoesViewModel> { PublicacoesViewModel(get(), get()) }

    viewModel<InicioProfessorViewModel> { InicioProfessorViewModel(get(), get()) }

    viewModel<CategoriasViewModel> {
        CategoriasViewModel(
            api = get()
        )
    }

    viewModel<ChamadosViewModel> {
        ChamadosViewModel(
            api = get(),
            sessaoUsuario = get()
        )
    }

    viewModel<RecuperacaoSenhaViewModel>{
        RecuperacaoSenhaViewModel(
            api = get()
        )
    }
}