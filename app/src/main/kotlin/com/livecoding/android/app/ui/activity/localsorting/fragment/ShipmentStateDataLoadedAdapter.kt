package com.livecoding.android.app.ui.activity.localsorting.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livecoding.android.app.databinding.ShipmentItemStateDataLoadedBinding
import com.livecoding.android.app.ui.model.Shipment

class ShipmentStateDataLoadedAdapter(
    private var shipmentList: List<Shipment>
) : RecyclerView.Adapter<ShipmentStateDataLoadedAdapter.ShipmentDataStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentDataStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShipmentItemStateDataLoadedBinding.inflate(layoutInflater, parent, false)

        return ShipmentDataStateViewHolder.create(binding)
    }

    override fun onBindViewHolder(holder: ShipmentDataStateViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun getItemCount(): Int {
        return shipmentList.size
    }

    fun sortItemByDateNearest() {
        shipmentList = shipmentList.sortedBy { it.date }
        notifyDataSetChanged()
    }

    fun sortItemByDateFarthest() {
        shipmentList = shipmentList.sortedByDescending { it.date }
        notifyDataSetChanged()
    }

    fun sortItemByPriceLowest() {
        shipmentList = shipmentList.sortedBy { it.price }
        notifyDataSetChanged()
    }

    fun sortItemByPriceHighest() {
        shipmentList = shipmentList.sortedByDescending { it.price }
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Shipment {
        return shipmentList[position]
    }

    class ShipmentDataStateViewHolder(
        private val binding: ShipmentItemStateDataLoadedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var shipment: Shipment? = null

        fun bindTo(shipment: Shipment?) {
            this.shipment = shipment

            shipment?.let {
                binding.tvOrigin.text = it.origin
                binding.tvDestination.text = it.destination
                binding.tvPrice.text = it.price.toString()
                binding.tvDate.text = it.date.toString()
            }
        }

        companion object {
            fun create(binding: ShipmentItemStateDataLoadedBinding): ShipmentDataStateViewHolder {
                return ShipmentDataStateViewHolder(binding)
            }
        }
    }
}