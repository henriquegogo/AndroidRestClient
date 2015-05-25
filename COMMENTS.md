# Comentários sobre desenvolvimento

Os comentários sobre o andamento do desenvolvimento estão todos descritos nas mensagens de commit.
A aplicação foi desenvolvida utilizando o Android Studio 1.2.1.1 em ambiente Debian

Os testes unitários estão listados na pasta app/src/androidTest/ e foram executados utilizando o Android Studio

Os testes de aceitação foram escritos com Calabash e Cucumber e estão listados na pasta features/
Para executá-los é necessário instalar o calabash conforme as instruções em:
https://github.com/calabash/calabash-android/blob/master/documentation/installation.md
e ter previamente buildado a aplicação.
Após as etapas acima concluídas, basta executar o script: ./runCalabashTests.sh

Dos requisitos pedidos não foi realizado o item 5 por falta de tempo.

Sobre o item 3 dos requisitos técnicos (acesso offline), a implementação está parcial. Por falta de tempo também.
