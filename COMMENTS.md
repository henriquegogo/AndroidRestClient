# Comentários sobre desenvolvimento

Os comentários sobre o andamento do desenvolvimento estão todos descritos nas mensagens de commit.
A aplicação foi desenvolvida utilizando o Android Studio 1.2.1.1 em ambiente Debian

Os testes unitários estão listados na pasta app/src/androidTest/ e foram executados utilizando o Android Studio

Os testes de aceitação foram escritos com Calabash e Cucumber e estão listados na pasta features/
Para executá-los é necessário instalar o calabash conforme as instruções em:
https://github.com/calabash/calabash-android/blob/master/documentation/installation.md
e ter previamente buildado a aplicação.
Após as etapas acima concluídas, basta executar o script: ./runCalabashTests.sh

A lista das partidas é salva e sempre carregada a partir do aparelho, para evitar requisições desnecessárias ao servidor.
Caso o usuário queira atualizar e assim fazer a requisição, ele pode fazer fazendo um swipe-refresh (scrollando para cima).
