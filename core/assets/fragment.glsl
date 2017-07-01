#version 120

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform sampler2D u_distortion_map;
uniform float u_time;

const float strength = .2f;

void main() {
    vec4 texture_color = texture2D(u_texture, v_texCoords);
    float alpha = texture_color.w;

    vec4 temp = mix(texture_color, strength*texture2D(u_distortion_map, vec2(v_texCoords.x + u_time, v_texCoords.y + u_time)), .3);
    temp.a = alpha;
    gl_FragColor = temp;
}
