# 🛠️ Guía de Contribución - CharmeetChic

Reglas y recomendaciones para contribuir de forma organizada y efectiva.

## 🌿 Flujo de trabajo con Git

### 🧾 Ramas principales

- `main`: rama base del proyecto, siempre funcional.
- `daniela`: rama de trabajo personal de Daniela.
- `berta`: rama de trabajo personal de Berta.

### 🌱 Ramas de funcionalidad

Cada nueva funcionalidad o arreglo debe desarrollarse en una nueva rama basada en tu rama personal:

git checkout daniela # o berta
git checkout -b feat/nombre-de-la-tarea


### 📌 Nombres recomendados para ramas

| Tipo de tarea     | Prefijo | Ejemplo                          |
|-------------------|---------|----------------------------------|
| Nueva función     | feat/   | feat/agregar-producto            |
| Corrección        | fix/    | fix/corregir-stock               |
| Refactorización   | ref/    | ref/reorganizar-controller       |
| Documentación     | docs/   | docs/actualizar-readme           |
| Pruebas           | test/   | test/producto-service            |

### 📤 Subir cambios

Una vez termines una tarea:

1. Agrega y haz commit:
git add .
git commit -m "feat: agregar producto al inventario"

2. Sube tu rama:
git push origin feat/agregar-producto


3. Haz un *pull request* hacia tu rama personal (`daniela` o `berta`) o hacia `main` si ya fue probado.

---

## 🧼 Convenciones de código

- Usa nombres descriptivos para clases, variables y métodos.
- Agrupa los archivos por entidad (modelo, repositorio, servicio, controlador).
- Incluye validaciones básicas y comentarios cuando sea necesario.
- Respeta la estructura del proyecto.

---

## ✅ Checklist antes de hacer `merge` a `main`

- [ ] Probé que la funcionalidad esté funcionando.
- [ ] Validé que no rompa otras partes del sistema.
- [ ] Agregué pruebas si aplica.
- [ ] Revisé el código (puede ser con la otra integrante).
- [ ] Hice `merge` desde mi rama personal hacia `main`.
