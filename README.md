# TripPlanner

Aplicativo Android para organização básica de viagens. O usuário informa o destino, seleciona o período da viagem e define atividades de interesse antes de visualizar o resumo.

Este projeto foi desenvolvido para a disciplina de Programação para Dispositivos Móveis.

## Funcionalidades

- Seleção de destino em etapas: país, estado e cidade.
- Estados e cidades filtrados de acordo com a escolha anterior.
- Seleção de data de ida e data de volta com `DatePickerDialog`.
- Validação de campos obrigatórios e do período da viagem.
- Escolha de atividades por caixas de seleção.
- Tela final com destino, período e atividades selecionadas.

## Fluxo da aplicação

1. O usuário seleciona o país.
2. O aplicativo libera os estados disponíveis para o país escolhido.
3. Após a escolha do estado, são exibidas apenas as cidades correspondentes.
4. O usuário informa as datas de ida e volta.
5. Na segunda tela, seleciona as atividades desejadas.
6. A última tela apresenta o resumo da viagem.

## Tecnologias utilizadas

- Kotlin
- Android SDK
- AndroidX
- XML para construção das interfaces
- Material Components

## Requisitos

- Android Studio
- JDK 11
- Android SDK com API 24 ou superior

## Como executar

1. Clone o repositório.

   ```bash
   git clone git@github.com:limarobs/TripPlanner.git
   ```

2. Abra a pasta do projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Selecione um emulador ou dispositivo Android conectado.
5. Execute o aplicativo pelo botão Run do Android Studio.

## Estrutura do projeto

```text
app/
├── src/main/java/com/example/tripplanner/
│   ├── MainActivity.kt
│   ├── ActivitiesActivity.kt
│   └── ConfirmationActivity.kt
├── src/main/res/layout/
│   ├── activity_main.xml
│   ├── activity_activities.xml
│   └── activity_confirmation.xml
└── build.gradle.kts
```

## Destinos disponíveis

Atualmente, o aplicativo disponibiliza exemplos de destinos no Brasil e em Portugal.

| País | Estado | Cidades |
| --- | --- | --- |
| Brasil | São Paulo, Rio de Janeiro e Rio Grande do Sul | São Paulo, Campinas, Rio de Janeiro, Niterói, Porto Alegre e Santiago |
| Portugal | Lisboa e Porto | Lisboa, Sintra, Porto e Vila Nova de Gaia |

## Autor

Roberto Lima
