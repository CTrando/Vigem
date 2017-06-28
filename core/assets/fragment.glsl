#version 120

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform sampler2D u_distortion_map;
uniform float u_time;

void main() {
    vec4 temp = mix(texture2D(u_texture, v_texCoords), texture2D(u_distortion_map, vec2(v_texCoords.x + u_time, v_texCoords.y + u_time)), .3);
    gl_FragColor = temp;
}
