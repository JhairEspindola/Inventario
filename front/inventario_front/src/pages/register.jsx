import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import Select from "react-select";
import Header from "../components/header";
import Footer from "../components/footer";
import "./register.css";

const defaultValues = {
  nombre: "",
  tipo: "producto",
  precioVenta: "",
  estadoReparacion: "recibido",
  tecnicoId: null,
};

const technicianOptions = [
  { value: "TEC01", label: "Ana López" },
  { value: "TEC02", label: "Carlos Ruiz" },
];

const Register = () => {
  const [isSaved, setIsSaved] = useState(false);
  const {
    register,
    handleSubmit,
    watch,
    control,
    reset,
    setValue,
    getValues,
    trigger,
    clearErrors,
    setFocus,
    formState: { errors, isSubmitting, isValid, dirtyFields },
  } = useForm({
    defaultValues,
    mode: "onBlur",
    shouldUnregister: true,
  });

  const tipoElemento = watch("tipo");

  // Este efecto observa el tipo de elemento y ajusta los campos del formulario
  // para que el usuario solo vea opciones válidas según la categoría seleccionada.
  useEffect(() => {
    setIsSaved(false);
    clearErrors(["precioVenta", "estadoReparacion"]);

    if (tipoElemento === "producto") {
      setValue("estadoReparacion", defaultValues.estadoReparacion);
      setValue("precioVenta", getValues("precioVenta"));
    } else if (tipoElemento === "equipo") {
      setValue("precioVenta", "");
      setValue("estadoReparacion", defaultValues.estadoReparacion);
    } else {
      setValue("precioVenta", "");
      setValue("estadoReparacion", undefined);
    }
  }, [clearErrors, getValues, setValue, tipoElemento]);

  // onSubmit valida que el formulario esté listo y guarda los datos actuales,
  // luego resetea el estado para mostrar la confirmación de registro.
  const onSubmit = async () => {
    const data = getValues();
    console.log("Guardando en base de datos:", data);
    setIsSaved(true);
    reset(defaultValues);
  };

  // onInvalid recibe los errores del formulario y coloca el foco en el primer
  // campo que no fue completado correctamente para mejorar la experiencia del usuario.
  const onInvalid = (formErrors) => {
    const firstInvalidField = Object.keys(formErrors)[0];
    if (firstInvalidField) {
      setFocus(firstInvalidField);
    }
  };

  // handleReset limpia el formulario y oculta el mensaje de éxito para dejar
  // el formulario listo para otra captura nueva.
  const handleReset = () => {
    reset(defaultValues);
    setIsSaved(false);
  };

  return (
    <>
      <Header />
      <main className="register-page">
        <section className="register-shell">
          <div className="register-heading">
            <p className="register-kicker">INVENTARIO</p>
            <h1>Registrar nuevo elemento</h1>
            <p>Completa la información para mantener tu inventario organizado y actualizado.</p>
          </div>

          <form
            onSubmit={handleSubmit(onSubmit, onInvalid)}
            className="register-form"
            noValidate
          >
            <div className="form-section">
              <div className="section-heading">
                <h2>Información general</h2>
                <p>Los campos marcados con * son obligatorios.</p>
              </div>

              <div className="form-grid">
                <div className="form-field form-field-full">
                  <label htmlFor="nombre">Nombre del elemento *</label>
                  <input
                    id="nombre"
                    type="text"
                    placeholder="Ej. Teclado mecánico"
                    aria-invalid={Boolean(errors.nombre)}
                    {...register("nombre", {
                      required: "El nombre es obligatorio.",
                      minLength: {
                        value: 3,
                        message: "Debe tener al menos 3 caracteres.",
                      },
                    })}
                  />
                  {errors.nombre && <span className="error">{errors.nombre.message}</span>}
                </div>

                <div className="form-field form-field-full">
                  <label htmlFor="tipo">Categoría en el sistema *</label>
                  <select
                    id="tipo"
                    aria-invalid={Boolean(errors.tipo)}
                    {...register("tipo", { required: "Selecciona una categoría." })}
                  >
                    <option value="producto">Producto para venta</option>
                    <option value="tecnologia">Tecnología (software/licencia)</option>
                    <option value="equipo">Equipo de cliente (reparación)</option>
                  </select>
                  {errors.tipo && <span className="error">{errors.tipo.message}</span>}
                </div>

                {tipoElemento === "producto" && (
                  <div className="form-field">
                    <label htmlFor="precioVenta">Precio de venta ($) *</label>
                    <input
                      id="precioVenta"
                      type="number"
                      min="0"
                      step="0.01"
                      placeholder="0.00"
                      aria-invalid={Boolean(errors.precioVenta)}
                      {...register("precioVenta", {
                        required: "El precio de venta es obligatorio.",
                        valueAsNumber: true,
                        min: { value: 0, message: "El precio no puede ser negativo." },
                      })}
                    />
                    {errors.precioVenta && (
                      <span className="error">{errors.precioVenta.message}</span>
                    )}
                  </div>
                )}

                {tipoElemento === "equipo" && (
                  <div className="form-field">
                    <label htmlFor="estadoReparacion">Estado de la reparación *</label>
                    <select
                      id="estadoReparacion"
                      aria-invalid={Boolean(errors.estadoReparacion)}
                      {...register("estadoReparacion", {
                        required: "Selecciona el estado de la reparación.",
                      })}
                    >
                      <option value="recibido">Recibido</option>
                      <option value="revisado">Revisado</option>
                      <option value="entregado">Entregado</option>
                    </select>
                    {errors.estadoReparacion && (
                      <span className="error">{errors.estadoReparacion.message}</span>
                    )}
                  </div>
                )}

                <div className="form-field form-field-full">
                  <label htmlFor="tecnicoId">Técnico responsable *</label>
                  <Controller
                    name="tecnicoId"
                    control={control}
                    rules={{ required: "Debes asignar un técnico." }}
                    render={({ field }) => (
                      <Select
                        {...field}
                        inputId="tecnicoId"
                        classNamePrefix="register-select"
                        options={technicianOptions}
                        placeholder="Buscar técnico..."
                        isClearable
                        onChange={(option) => {
                          field.onChange(option);
                          void trigger("tecnicoId");
                        }}
                      />
                    )}
                  />
                  {errors.tecnicoId && <span className="error">{errors.tecnicoId.message}</span>}
                </div>
              </div>
            </div>

            <div className="form-footer">
              <span className="form-status" aria-live="polite">
                {isSaved
                  ? "Elemento registrado correctamente."
                  : dirtyFields.nombre && isValid
                    ? "Formulario listo para guardar."
                    : ""}
              </span>
              <div className="form-actions">
                <button type="button" className="button button-secondary" onClick={handleReset}>
                  Limpiar
                </button>
                <button type="submit" className="button button-primary" disabled={isSubmitting}>
                  {isSubmitting ? "Guardando..." : "Registrar elemento"}
                </button>
              </div>
            </div>
          </form>
        </section>
      </main>
      <Footer />
    </>
  );
};

export default Register;
