## Henrique Gogó
**Globo.com: coding challenge**

====================
#### Considerações Gerais
Você deverá usar este repositório como o repo principal do projeto, i.e., todos os seus commits devem estar registrados aqui, pois queremos ver como você trabalha.

**Registre tudo**: testes que forem executados, ideias que gostaria de implementar se tivesse mais tempo (explique como você as resolveria, se houvesse tempo), decisões que forem tomadas e seus porquês, arquiteturas que forem testadas e os motivos de terem sido modificadas ou abandonadas. Crie um arquivo COMMENTS.md ou HISTORY.md no repositório para registrar essas reflexões e decisões.

=====================
#### O Desafio

O desafio que você deverá desenvolver é o aplicativo "Placar GE". Nele, podemos acompanhar o placar dos principais jogos do Brasileirão.

O aplicativo deverá funcionar em dispositivos iOS 7+ **OU** Android 4+. Considere que o testaremos em aparelhos com resolução de tela de 320px ou superior, tanto na posição *portrait* quanto em *landscape*.

============================
#### Regras de negócio

1. O aplicativo deve listar, em sua tela inicial, o placar dos jogos que estão disponibilizados através da um json de exemplo que simula uma API do Globoesporte, exibindo o nome dos times, escudos, placar, local e horário de cada jogo. Você deve acessá-la e consumí-la através [daqui](http://matchesjson.herokuapp.com/matches.json).

2. Nesta tela inicial, deve ser possível visualizar todos os placares vindos dessa API. Considere que todos os jogos são jogos diferentes (mesmo que alguns tenham a mesma url de jogo com apenas o placar ou a data diferente). Para a interface gráfica dessa listagem, você pode se basear nas telas dos nossos aplicativos "Placar GE" para [iOs](https://itunes.apple.com/br/app/placar-ge/id947071703?mt=8) e [Android](https://play.google.com/store/apps/details?id=com.globoesporte.placarge).

3. Ao se tocar na linha de um determinado placar, o usuário é redirecionado para a página Web com os detalhes do jogo correspondente (dentro do próprio aplicativo). O link para esta página do jogo também é disponibilizado através da mesma API.

4. A partir da tela dos detalhes do jogo, o usuário poderá voltar para a tela inicial.

5. O usuário também pode escolher um determinado time para ver apenas os placares de seus jogos (considere que a lista de todos os times disponíveis para escolher é uma lista única de todos os times mandantes e visitantes presentes no json de exemplo). Fica a seu critério como exibir esta opção no aplicativo. Use a sua criatividade e torne interessante a experiência para o usuário.

6. O usuário deve ter uma forma de atualizar a lista de jogos (imagina que a API pudesse mudar a sua lista de jogos todo dia).

7. Caso tenha escolhido fazer em Android, pediremos para enviar para nós por email a apk do aplicativo.

#### Requisitos técnicos

1. Seu código deve conter testes automatizados funcionais e de interface para os principais pontos do aplicativo.

2. Esperamos que o aplicativo faça o menor número de requisições possíveis para a API do Globoesporte de exemplo.

3. Se a API ficar indisponível ou o celular do usuário fique sem internet, esperamos que o aplicativo continue funcionando e permita pegar a lista de jogos novamente. 

===============================================
#### O que será avaliado na sua solução?

1. As funcionalidades listadas anteriormente devem estar presentes na sua solução. É possível mudar o escopo do que foi pedido, mas desde que fique claro nos seus comentários o que mudou e qual foi o motivo da mudança.
2. Seu código será observado por uma equipe de desenvolvedores que avaliarão a simplicidade e clareza da solução, a arquitetura, documentação, estilo de código, testes unitários, testes funcionais, nível de automação dos testes, o design da interface e a implementação do código.

=============
#### Dicas

- Use ferramentas e bibliotecas open source, mas documente as decisões e os porquês;
- Automatize o que for possível;
- Em caso de dúvidas, pergunte.
