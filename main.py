
from collections import namedtuple
from random import choices
from typing import List


Genome = List[int]
Population = List[Genome]
Thing = namedtuple('Thing', ['name', 'value', 'weight'])

things = [
    Thing('Laptop', 500, 2200),
    Thing('Headphones', 150, 160),
    Thing('Wallet', 150, 25),
    Thing('Candy', 1, 1),
    Thing('Ball', 10, 150),
    Thing('Ipad', 500, 2200),
]

more_things = [
    Thing('Socks', 10, 50),
    Thing('Books', 300, 500),
    Thing('Trousers', 250, 750),
    Thing('Phone', 500, 200),
    Thing('Pillow', 150, 300),
    Thing('Glasses', 100, 75),
] + things



def generate_genome(length: int) -> Genome:
    return choices([0,1], k= length)

def generate_populationsize(size: int, geneme_lenght:int) -> Population:
    return [generate_genome(generate_genome) for _ in range(size)]

def fitness(genome: Genome, things: [Thing],weight_limit: int) -> int:
    if len(genome) != len(things):
        raise ValueError("genome and things must be of the same lenght")
    
    weight = 0
    value = 0
    
    for i, thing in enumerate(things):
        if genome[i] == 1:
            weight += thing.weight
            value += thing.value
            
            if weight > weight_limit:
                return 0
        
    return value
