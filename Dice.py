import random

class Dice:
    def __init__(self, p_faces: list[float]):
        total = 0
        soma = 0
        self.p_faces = []
        self.accumulated_p = []
        
        for p in p_faces:
            total += p
            
        for i, p in enumerate(p_faces):
            self.p_faces.append(p/total)
            if len(self.accumulated_p) == 0:
                self.accumulated_p.append(self.p_faces[-1])
            else:
                self.accumulated_p.append(self.p_faces[-1] + self.accumulated_p[-1])
            soma += (1 + i)*p
    
        self.predicted_mean = soma/total
        
    def balanced(sides: int):
        return Dice([1 for x in range(sides)])
    
    def loaded(sides: int):
        return Dice([random.random() for x in range(sides)])
    
    def high_loaded(sides: int):
        return Dice([x+1 for x in range(sides)])
    
    def low_loaded(sides: int):
        aux = [x+1 for x in range(sides)]
        aux.reverse()
        return Dice(aux)
    
    def roll(self) -> int:
        rn = random.random()
        
        for i, p in enumerate(self.accumulated_p):
            if rn <= p:
                return i+1
        return len(self.p_faces)
    
    def roll_times(self, times: int) -> list[int]:
        result = []
        for x in range(times):
            result.append(self.roll())
        return result
        
    
    def __str__(self):
        return f"Probabilty of each face: {self.p_faces}\nPredicted mean: {self.predicted_mean}"
