

// ========== FONTE DISLEXIA ==========
const btnAlternarFonteDislexia = document.getElementById('btn-acessibilidade-alternar-fonte');

const setFonteDislexia = (ativar) => {
    const root = document.documentElement;
    const fonte = ativar ? "'OpenDyslexic', sans-serif" : "'Poppins', sans-serif";
    root.style.setProperty('--font-family-padrao', fonte);
    localStorage.setItem('acessibilidadeFonteDislexia', ativar.toString());
};

if (btnAlternarFonteDislexia) {
    btnAlternarFonteDislexia.addEventListener('click', () => {
        const fonteAtiva = localStorage.getItem('acessibilidadeFonteDislexia') === 'true';
        setFonteDislexia(!fonteAtiva);
    });
}

// ========== PALETA DALTONISMO ==========
const btnAlternarPaletaDaltonismo = document.getElementById('btn-acessibilidade-alternar-paleta');

const paletaNormal = {
    "--bg-0": "#FFFFFF",
    "--bg-1": "#050835",
    "--bg-2": "#3B63A8",
    "--bg-3": "#3D94F5",
    "--bg-4": "#F4BC33",
    "--bg-5": "#FE728D",
    "--bg-6": "#e7e7e7",
    "--font-1": "#2A2829",
    "--font-2": "#FFFFFF",
    "--font-3": "#050835",
    "--font-4": "#3b63a8"
};

const paletaDaltonica = {
    "--bg-0": "#FFFFFF",
    "--bg-1": "#003f5c",
    "--bg-2": "#2f4b7c",
    "--bg-3": "#665191",
    "--bg-4": "#a05195",
    "--bg-5": "#ff7c43",
    "--bg-6": "#f0f0f0",
    "--font-1": "#000000",
    "--font-2": "#FFFFFF",
    "--font-3": "#333333",
    "--font-4": "#2f4b7c"
};

const paletaGraficosNormal = {
    "--cor-grafico-1": "rgba(255, 99, 132, 1)",
    "--cor-grafico-2": "rgba(54, 162, 235, 1)",
    "--cor-grafico-3": "rgba(255, 206, 86, 1)",
    "--cor-grafico-4": "rgba(75, 192, 192, 1)",
    "--cor-grafico-5": "rgba(153, 105, 255, 1)",
    "--sombra-grafico-1": "0 0 20px 0 rgba(255, 99, 132, 0.2)",
    "--sombra-grafico-2": "0 0 20px 0 rgba(54, 162, 235, 0.2)",
    "--sombra-grafico-3": "0 0 20px 0 rgba(255, 206, 86, 0.2)",
    "--sombra-grafico-4": "0 0 20px 0 rgba(75, 192, 192, 0.2)",
    "--sombra-grafico-5": "0 0 20px 0 rgba(153, 105, 255, 0.2)",
};

const paletaGraficosDaltonica = {
    "--cor-grafico-1": "rgba(230, 159, 0, 1)",
    "--cor-grafico-2": "rgba(0, 114, 178, 1)",
    "--cor-grafico-3": "rgba(240, 228, 66, 1)",
    "--cor-grafico-4": "rgba(86, 180, 233, 1)",
    "--cor-grafico-5": "rgba(204, 121, 167, 1)",
    "--sombra-grafico-1": "0 0 20px 0 rgba(230, 159, 0, 0.2)",
    "--sombra-grafico-2": "0 0 20px 0 rgba(0, 114, 178, 0.2)",
    "--sombra-grafico-3": "0 0 20px 0 rgba(240, 228, 66, 0.2)",
    "--sombra-grafico-4": "0 0 20px 0 rgba(86, 180, 233, 0.2)",
    "--sombra-grafico-5": "0 0 20px 0 rgba(204, 121, 167, 0.2)",
};

const aplicarPaleta = (paleta) => {
    const root = document.documentElement;
    for (const variavel in paleta) {
        root.style.setProperty(variavel, paleta[variavel]);
    }
};

const alternarPaleta = () => {
    const modoDaltonicoAtivo = localStorage.getItem("acessibilidadePaletaDaltonica") === "true";
    if (modoDaltonicoAtivo) {
        aplicarPaleta({ ...paletaNormal, ...paletaGraficosNormal });
        localStorage.setItem("acessibilidadePaletaDaltonica", "false");
    } else {
        aplicarPaleta({ ...paletaDaltonica, ...paletaGraficosDaltonica });
        localStorage.setItem("acessibilidadePaletaDaltonica", "true");
    }

};

if (btnAlternarPaletaDaltonismo) {
    btnAlternarPaletaDaltonismo.addEventListener("click", alternarPaleta);
}

// ========== APLICAR CONFIGURAÇÕES AO CARREGAR A PÁGINA ==========
window.addEventListener("DOMContentLoaded", () => {
    // Aplicar fonte salva
    const fonteAtiva = localStorage.getItem('acessibilidadeFonteDislexia') === 'true';
    setFonteDislexia(fonteAtiva);

    // Aplicar paleta salva
    const modoDaltonicoAtivo = localStorage.getItem("acessibilidadePaletaDaltonica") === "true";
    aplicarPaleta(modoDaltonicoAtivo ? { ...paletaDaltonica, ...paletaGraficosDaltonica } : { ...paletaNormal, ...paletaGraficosNormal });
});
