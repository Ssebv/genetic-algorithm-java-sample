## Algoritmo Genético para la Resolución del Problema de la Mochila

Algoritmo genético para resolver el **Problema de la Mochila**

Diseñado para encontrar la mejor selección de elementos de una lista de "cosas" (things) para colocar en una mochila, teniendo en cuenta el límite de peso de la mochila. El objetivo es maximizar el valor de los elementos seleccionados sin exceder el peso máximo de la mochila. El código utiliza un enfoque de algoritmo genético para encontrar la solución óptima.

- **Genoma**: Representa una solución potencial y consiste en una lista de bits (0 o 1), donde cada bit indica si se incluye o no un elemento en la mochila.

- **Población**: Es un conjunto de soluciones potenciales (genomas) que evoluciona con el tiempo.

- **Función de Aptitud (FitnessFunc)**: Evalúa qué tan buena es una solución en función del valor de los elementos seleccionados y si se cumple el límite de peso.

- **Función de Población (PopulationFunc)**: Genera una población inicial de soluciones potenciales.

- **Función de Selección (SelectionFunc)**: Selecciona dos soluciones de la población actual para reproducirse.

- **Función de Cruzamiento (CrossoverFunc)**: Combina dos soluciones para crear nuevas soluciones.

- **Función de Mutación (MutationFunc)**: Realiza cambios aleatorios en una solución.

- **Ejecución del Algoritmo (run_evolution)**: Ejecuta la evolución de la población a lo largo de varias generaciones hasta que se alcanza un límite de generaciones o se encuentra una solución que cumple el límite de aptitud.

- **Conversión de Genoma a Cosas (genome_to_things)**: Convierte una solución en una lista de elementos seleccionados.

### Algoritmo Genético:

Un algoritmo genético es una técnica de optimización inspirada en la evolución biológica. Funciona creando una población de soluciones potenciales (genomas) y utilizando operadores de selección, cruzamiento y mutación para evolucionar la población a lo largo de generaciones. El objetivo es encontrar la mejor solución posible al problema.

### Variables a Considerar:

- **Genoma**: Representa una solución potencial y se define como una lista de bits (0 o 1).

- **Población**: Es el conjunto de soluciones potenciales que evoluciona a lo largo del tiempo.

- **Función de Aptitud (FitnessFunc)**: Evalúa la calidad de una solución en función de la aptitud del genoma, que en este caso se mide como el valor de los elementos seleccionados en la mochila.

- **Función de Selección (SelectionFunc)**: Selecciona las soluciones más aptas para la reproducción.

- **Función de Cruzamiento (CrossoverFunc)**: Combina dos soluciones para crear nuevas soluciones que heredan características de sus padres.

- **Función de Mutación (MutationFunc)**: Introduce cambios aleatorios en una solución para aumentar la diversidad genética de la población.

- **Límite de Generaciones**: El número máximo de generaciones que se ejecutarán antes de que se detenga el algoritmo.

- **Límite de Aptitud (Fitness Limit)**: El valor de aptitud mínimo que debe alcanzar una solución para considerarse aceptable.

## Ejemplo

**Ejemplo con solo "things":**

```python
number of generations: 2
time: 0.00020623207092285156s
best solution: ['Laptop', 'Headphones', 'Coffee Mug', 'Water Bottle']
```

En este ejemplo, el algoritmo se ejecutó durante solo 2 generaciones antes de encontrar una solución óptima. Los elementos seleccionados en la mochila son "Laptop," "Headphones," "Coffee Mug," y "Water Bottle."

**Ejemplo con "more_things":**

```python
number of generations: 13
time: 0.0008139610290527344s
best solution: ['Mints', 'Socks', 'Tissues', 'Phone', 'Baseball Cap', 'Laptop', 'Headphones', 'Water Bottle']
```

En este ejemplo, el algoritmo genético se ejecutó durante 13 generaciones y encontró una solución óptima que maximiza el valor de la mochila sin exceder el límite de peso. Los elementos seleccionados son "Mints," "Socks," "Tissues," "Phone," "Baseball Cap," "Laptop," "Headphones," y "Water Bottle."

Ambos ejemplos muestran cómo el algoritmo genético es capaz de encontrar soluciones que optimizan el valor de la mochila dentro de los límites de peso establecidos. El tiempo de ejecución puede variar según la complejidad del problema y la configuración del algoritmo.