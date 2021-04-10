# mutant-mercado-libre

Este proyecto expone un api rest para analizar cadenas de adn

Para correr localmente el proyecto, lo puede hacer por eclipse, mediante la opción importar maven project. 
El proyecto está construido con SpringBoot por lo cual bastará con ejecutar la clase main (DnaApplication.java).

Para probar el api que está desplegada en la nube de google lo puede hacer mediante los siguientes endpoints:

1. Analizar ADN: https://mutant-mercado-libre.rj.r.appspot.com/mutant
   
   Consumir mediante POST con el siguiente body:
   
   {
   "dna": ["atcgatcg","gctagcta", "atcgatcg", "ctgactga", "atctatcg", "ctgcccca"]
   }
   
   El servicio responde estado http 200 en caso de encontrar que hay ADN mutante (4 caraceteres del mismo tipo horizontal, vertical o diagonalmente)
   En caso contrario responderá estado 403.
   
2. Estadísticas: https://mutant-mercado-libre.rj.r.appspot.com/stats
   
   Consumir mediante GET
   
   Este servicio responde lo siguiente:
   
   {
    "mutantCount": 2,
    "humanCount": 4,
    "ratio": 0.33333333333333337
   }
   
   mutantCount es el número de veces que se ha detectado ADN mutante
   
   humanCount es el número de veces que se ha detectado ADN humano
   
   ratio es el porcentaje que representa mutantCount respecto al total de detecciones
   
