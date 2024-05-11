package estudo.serviceimc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Imc {

    private double weight;
    private double height;
    private double imc;
    private String msg;
    private String diet;

    public Imc(double weight, double height) {
        var imc = calcImc(weight, height);
        this.weight = weight;
        this.height = height;
        this.imc = imc;
        defineDiet(imc);
    }

    private double calcImc(double weight, double height) {
        return (weight / Math.pow((height / 100), 2));
    }

    private void defineDiet(double imc) {
    if (imc < 18.5) {
        setMsg("Você está abaixo do peso. Tente consumir alimentos ricos em proteínas e calorias para ganhar peso.");
        setDiet("Menu recomendado: Café da manhã: Omelete com vegetais; Almoço: Frango grelhado com arroz integral e legumes; Jantar: Salmão assado com batata doce e brócolis.");
    } else if (imc <= 24.9) {
        setMsg("Você está com peso normal. Continue com uma dieta equilibrada e pratique exercícios regularmente.");
        setDiet("Menu recomendado: Café da manhã: Aveia com frutas e iogurte; Almoço: Salada verde com frango grelhado; Jantar: Peixe cozido com quinoa e legumes.");
    } else if (imc <= 29.9) {
        setMsg("Você está com sobrepeso. Reduza a ingestão de calorias e faça exercícios regularmente.");
        setDiet("Menu recomendado: Café da manhã: Smoothie de frutas e vegetais; Almoço: Sanduíche de peru e salada; Jantar: Espaguete integral com molho de tomate caseiro.");
    } else {
        setMsg("Você está obeso. Consulte um nutricionista para um plano de dieta personalizado.");
        setDiet("Menu recomendado: Café da manhã: Omelete de claras com espinafre; Almoço: Salada de grãos com frango grelhado; Jantar: Sopa de legumes com carne magra.");
    }
}


}
