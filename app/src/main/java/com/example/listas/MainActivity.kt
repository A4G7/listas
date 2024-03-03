package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val especialidades = listOf(
            Especialidades(1, "Seleccione un Puesto"),
            Especialidades(2, "Gerente"),
            Especialidades(3, "Secretaria"),
            Especialidades(4, "Asistente"),
            Especialidades(5, "Supervisor"),
            Especialidades(6, "Contador"),
            Especialidades(7, "Programador"),
            Especialidades(8, "Analista")
        )

        val empleados = listOf(
            Empleado(1, "Seleccione una especialidad", "", "","","Seleccione un Puesto"),
            Empleado(2, "Seleccione un empleado", "", "","", "Gerente"),
            Empleado(3, "Seleccione un empleado", "", "","", "Secretaria"),
            Empleado(4, "Seleccione un empleado", "", "","", "Asistente"),
            Empleado(5, "Seleccione un empleado", "", "","", "Supervisor"),
            Empleado(6, "Seleccione un empleado", "", "","", "Contador"),
            Empleado(7, "Seleccione un empleado", "", "","", "Programador"),
            Empleado(8, "Seleccione un empleado", "", "","", "Analista"),
            Empleado(9, "Juan", "Pérez", "987654321","15/05/1980", "Gerente"),
            Empleado(10, "María", "López", "456789123","20/12/1990", "Secretaria"),
            Empleado(11, "Carlos", "García", "321654987","10/08/1975", "Gerente"),
            Empleado(12, "Laura", "Martínez", "789456123","25/03/1988", "Secretaria"),
            Empleado(13, "Pedro", "Rodríguez", "123456789","02/11/1995", "Gerente"),
            Empleado(14, "Ana", "Gómez", "234567890","07/09/1983", "Programador"),
            Empleado(15, "Luis", "Hernández", "678901234","14/06/1972", "Programador"),
            Empleado(16, "Sofia", "Morales", "345678901","29/11/1998", "Asistente"),
            Empleado(17, "Javier", "Torres", "890123456","03/04/1985", "Supervisor"),
            Empleado(18, "Carolina", "Díaz", "456789012","18/01/1993", "Asistente"),
            Empleado(19, "David", "Ruiz", "901234567","22/08/1978", "Programador"),
            Empleado(20, "Marta", "Sánchez", "567890123","10/05/1990", "Analista"),
            Empleado(21, "Alejandro", "Torres", "345678901","12/03/1992", "Contador"),
        )

        val spinnerEspecialidades: Spinner = findViewById(R.id.spinnerEspecialidades)
        val spinnerEmpleados: Spinner = findViewById(R.id.spinnerEmpleados)

        val nombre: TextView = findViewById(R.id.textViewNombre)
        val apellido: TextView = findViewById(R.id.textViewApellido)
        val dni: TextView = findViewById(R.id.textViewDni)
        val fecha: TextView = findViewById(R.id.textViewFecha)
        val especialidad: TextView = findViewById(R.id.textViewEspecialidad)

        val adapterEspecialidades = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            especialidades.map { it.nombre }
        )
        adapterEspecialidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEspecialidades.adapter = adapterEspecialidades

        spinnerEspecialidades.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val idEspecialidadSeleccionada = especialidades[position].nombre
                val empleadosFiltrados = empleados.filter { it.especialidad == idEspecialidadSeleccionada }
                val nombresEmpleados = empleadosFiltrados.map { it.nombre }

                val adapterEmpleados = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_item,
                    nombresEmpleados
                )
                adapterEmpleados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerEmpleados.adapter = adapterEmpleados

                nombre.text = ""
                apellido.text = ""
                dni.text = ""
                fecha.text = ""
                especialidad.text = ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })


        spinnerEmpleados.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val empleadoSeleccionado = empleados.filter { it.especialidad == especialidades[spinnerEspecialidades.selectedItemPosition].nombre }[position]

                if(empleadoSeleccionado.especialidad === "Seleccione un Puesto"){
                    nombre.text = ""
                    apellido.text = ""
                    dni.text = ""
                    fecha.text = ""
                    especialidad.text = ""
                }else if(empleadoSeleccionado.apellidos === ""){
                    nombre.text = ""
                    apellido.text = ""
                    dni.text = ""
                    fecha.text = ""
                    especialidad.text = ""
                } else{
                    nombre.text = empleadoSeleccionado.nombre
                    apellido.text = empleadoSeleccionado.apellidos
                    dni.text = empleadoSeleccionado.dni
                    fecha.text = empleadoSeleccionado.fecha
                    especialidad.text = empleadoSeleccionado.especialidad
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }
}