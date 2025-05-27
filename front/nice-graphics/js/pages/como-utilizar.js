console.log("a")
import { adicionarGrafico, prepararDados, definirContainer} from '../utils/adicionar-grafico-na-tela.js'

definirContainer("container-grafico-como-utilizar")

let jsonInicial = prepararDados([
    {
        "description": "Gráfico de demonstração",
        "type": "pie",
        "numberRepresented": "Número da demonstração",
        "data": [
            {
                "field": "Demo 1",
                "value": 1000
            },
            {
                "field": "Demo 2",
                "value": 700
            },
            {
                "field": "Demo 3",
                "value": 600
            },
            {
                "field": "Demo 4",
                "value": 400
            },
            {
                "field": "Demo 5",
                "value": 300
            },
            {
                "field": "Demo 6",
                "value": 200
            }
        ]
    }
])

adicionarGrafico(jsonInicial[0])






