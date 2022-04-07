Instructions de compilation apres avoir dézip le dossier :
1- Ouvrir le terminal
2- Accéder au répertoire du code source via la commande 'cd CheminTéléchargement/hummel_zerhouni/CodeSource'
3- Compiler le package de la gestion des exceptions via la commande 'javac ./projetexceptions/*.java'
4- Compiler le code source via la commande 'javac *.java'
5- Pour l'exécution, deux choix s'offrent à vous :
  a) Si vous souhaitez que votre code lise les instructions via une interaction avec le terminal à chaque pas de temps, veuillez exécuter avec la commande :
java Main 0 m n treso
  b) Si vous souhaitez que votre code lise les instructions via un fichier, ligne par ligne, veuillez exécuter avec la commande :
java Main fichierTest/fichier.txt m n treso
avec fichier.txt -> Nom du fichier dans le dossier fichierTest, m = nombre de rangées de l'entrepot, n = longueur de chaque rangée de l'entrepot, treso = la trésorerie initiale
Quelque soit votre choix, le terminal proposera avant chaque commande de vous montrer la liste des chefs, votre trésorerie, ainsi que votre stock, en tapant 'o'.
Ensuite, si vous choisissez a), vous devrez taper votre consigne à chaque pas de temps, chaque fois que le terminal le propose, l'une des trois consignes :
rien
lot <nom> <poids> <prix> <volume>
meuble <nom> <pieceMaison> <dureeConstruction> <typeLot1> <volumeLot1> <typeLot2> <volumeLot2> ...
Enfin, si vous choisissez b), vous devrez taper la même chose, mais dans un fichier, et ce en respectant le fait qu'il y ait une et une seule consigne par ligne. Sauf qu'en plus, le numéro de la consigne 'id' devra apparaitre. Ce sera donc selon le modèle suivant :
<id> rien
<id> lot <nom> <poids> <prix> <volume>
<id> meuble <nom> <pieceMaison> <dur´eeConstruction> <typeLot1> <volumeLot1> <typeLot2> <volumeLot2> ...
6- Le terminal propose 2 stratégie de stockage, au choix (en tapant 1 ou 2). Celles si seront expliquée sur le compte-rendu.
