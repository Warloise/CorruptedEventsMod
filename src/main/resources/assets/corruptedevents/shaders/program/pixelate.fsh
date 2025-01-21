#version 150

uniform sampler2D DiffuseSampler;
uniform float PixelSize;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec2 pixelatedCoord = floor(texCoord * PixelSize) / PixelSize;
    vec4 color = texture(DiffuseSampler, pixelatedCoord);
    fragColor = color;
}