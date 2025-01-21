#version 150

uniform sampler2D DiffuseSampler;
uniform float Time;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec2 uv = texCoord;

    // Generar un tiempo oscilante (de ida y vuelta) usando una onda seno
    float t = sin(Time * 2.0) * 0.5 + 0.5; // Oscila entre 0 y 1

    // CRT effect: curvature (reducida)
    uv = uv * 2.0 - 1.0; // Normaliza a rango [-1, 1]
    float curvature = 0.05; // Curvatura ligera
    uv *= mat2(1.0, -curvature * uv.y, curvature * uv.x, 1.0);
    uv = uv * 0.5 + 0.5; // Vuelve al rango [0, 1]

    // CRT effect: scanlines
    float scanline = 0.98 + 0.02 * sin(uv.y * 1000.0 + Time * 10.0);

    // CRT effect: distortion (ajustado para movimiento de ida y vuelta)
    uv.x += 0.001 * sin(uv.y * 20.0 + t * 3.14159); // Desplazamiento basado en t
    uv.y += 0.001 * cos(uv.x * 20.0 + t * 3.14159);

    // CRT effect: chromatic aberration
    float chromaDist = 0.001; // Aberración cromática más sutil
    vec3 color;
    color.r = texture(DiffuseSampler, uv + vec2(chromaDist, 0.0)).r;  // Canal rojo desplazado
    color.g = texture(DiffuseSampler, uv).g;                         // Canal verde sin cambios
    color.b = texture(DiffuseSampler, uv - vec2(chromaDist, 0.0)).b;  // Canal azul desplazado

    // CRT effect: vignette (suavizado)
    float vignette = smoothstep(1.0, 0.6, length(uv - 0.5));
    color *= vignette;

    // CRT effect: noise (estático dinámico)
    float noise = (fract(sin(dot(uv * vec2(12.9898, 78.233), vec2(43758.5453 + Time))) * 43758.5453) - 0.5) * 0.01;
    color += noise;

    // Combina los efectos y aplica scanlines
    color *= scanline;

    fragColor = vec4(color, 1.0);
}
