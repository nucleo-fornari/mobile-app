package com.example.nucleofornari.application.di

import com.example.nucleofornari.data.remote.EventoApi
import com.example.nucleofornari.data.remote.EventoApiService
import com.example.nucleofornari.data.remote.UsuarioApi
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.presentation.common.component.calendar.CalendarViewModel
import com.example.nucleofornari.presentation.screen.auth.login.LoginViewModel
import com.example.nucleofornari.presentation.screen.auth.redefinicao_senha.RecuperacaoSenhaViewModel
import com.example.nucleofornari.presentation.screen.responsavel.agenda.AgendaViewModel
import com.example.nucleofornari.presentation.screen.professor.categorias.CategoriasViewModel
import com.example.nucleofornari.presentation.screen.professor.chamados.ChamadosViewModel
import com.example.nucleofornari.presentation.screen.professor.inicio.InicioProfessorViewModel
import com.example.nucleofornari.presentation.screen.professor.relatorio.RelatorioProfessorViewModel
import com.example.nucleofornari.presentation.screen.responsavel.publicacoes.PublicacoesViewModel
import com.example.nucleofornari.presentation.screen.responsavel.reunioes_solicitadas.ReunioesSolicitadasViewModel
import com.example.nucleofornari.presentation.screen.responsavel.reunioes.ReunioesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduloGeral = module {
    single<com.example.nucleofornari.domain.model.SessaoUsuario> {
        com.example.nucleofornari.domain.model.SessaoUsuario()
    }
}

val moduloApiReal = module {
    factory<UsuarioApiService> {
        UsuarioApi.getApi(get<com.example.nucleofornari.domain.model.SessaoUsuario>().token)
    }

    factory<EventoApiService> {
        EventoApi.getApi(get<com.example.nucleofornari.domain.model.SessaoUsuario>().token)
    }

    viewModel<LoginViewModel> { LoginViewModel(get(),get()) }
    viewModel<AgendaViewModel> { AgendaViewModel(get(), get(), androidContext()) }
    viewModel<PublicacoesViewModel> { PublicacoesViewModel(get(), get(), androidContext()) }
    viewModel<CategoriasViewModel> { CategoriasViewModel(get(), androidContext()) }
    viewModel<ChamadosViewModel> { ChamadosViewModel(get(), get(), androidContext()) }
    viewModel<RecuperacaoSenhaViewModel>{ RecuperacaoSenhaViewModel(get()) }
    viewModel<CalendarViewModel>{ CalendarViewModel(get(), get())}
    viewModel<InicioProfessorViewModel>{ InicioProfessorViewModel(get(), get(), androidContext()) }
    viewModel<RelatorioProfessorViewModel> { RelatorioProfessorViewModel(get(), get(), androidContext()) }
    viewModel<ReunioesViewModel> { ReunioesViewModel(get(), get(), androidContext()) }
    viewModel<ReunioesSolicitadasViewModel> { ReunioesSolicitadasViewModel(get(), get(), androidContext()) }
}