package example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// MainActivity para contener los fragmentos
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar el primer fragmento al iniciar la actividad
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ImageSelectorFragment()) // Fragmento inicial
                .commit()
        }
    }
}
