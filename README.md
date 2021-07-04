# folha-de-ponto


#### GITMOJI

* Para utilizar o gitmoji basta colocar o seguinte comando no seu terminal:
```bash
git commit -am ":nomedoemoji: mensagem" -m "outra mensagem"
```


#### SONARQUBE

* **Primeiro passo:** instalar o docker-compose.

* **Segundo passo:** Subir o SonarQube com o comando abaixo
```bash
docker-compose -f sonarqube-h2.yml up
```

* **Terceiro passo:** Abrir o navegador com a URL localhost:9000

* **Quarto passo:** Para fazer login no SonarQube, existe uma senha e login padrão: admin(login), admin(senha). 

* **Quinto passo:** Suba o projeto no SonarQube usando o comando abaixo
```bash
./gradlew sonarqube
```


* **Sexto passo:** Para derrubar o SonarQube
```bash
docker-compose -f sonarqube-h2.yml down
```