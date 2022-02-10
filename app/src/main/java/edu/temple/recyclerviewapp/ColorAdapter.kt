package edu.temple.recyclerviewapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(_colorObjects : Array<ColorObject>, _myFunc : (ColorObject)->Unit) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>(){

    val colorObjects = _colorObjects
    val myEventHandlingFunc = _myFunc

    // Create view holder (defined as 'inner' class to access members of outer class
    inner class ColorViewHolder(_view: View) : RecyclerView.ViewHolder(_view) {
        val colorNameTextView = _view.findViewById<TextView>(R.id.colorNameTextView)
        val colorDisplayView = _view.findViewById<View>(R.id.colorDisplayView)

        // Initialized when new binding is invoked
        lateinit var colorObject: ColorObject
        init {
            _view.setOnClickListener{myEventHandlingFunc(colorObject)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        // Inflate layout file instead of creating views in code
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_layout, parent, false)
        return ColorViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        // Manipulate view attributes based on item at 'position'
        holder.colorNameTextView.text = colorObjects[position].name
        holder.colorDisplayView.setBackgroundColor(Color.parseColor(colorObjects[position].code))

        // Add object at 'position' to ViewHolder
        holder.colorObject = colorObjects[position]
    }

    override fun getItemCount(): Int {
        return colorObjects.size
    }
}