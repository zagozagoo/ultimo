PRESTA ATENÇÃOOOOOO

tem um handler de erro já configurado!!!!!

O que ele faz? toda vez que você jogar uma exception do tipo AppResponseException, ele vai
capturar automaticamente o erro e mandar a mensagem e o status code como resposta.

TEM EXEMPLO NO TEST CONTROLLER



Também fizemos os DTO's pra payload e o de user pra response, já que não é legal mandar a senha na response né, então ele filtra

1 - adm
2 - gerente
3 - colaborador