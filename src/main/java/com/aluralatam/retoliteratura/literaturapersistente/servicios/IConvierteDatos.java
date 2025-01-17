package com.aluralatam.retoliteratura.literaturapersistente.servicios;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
