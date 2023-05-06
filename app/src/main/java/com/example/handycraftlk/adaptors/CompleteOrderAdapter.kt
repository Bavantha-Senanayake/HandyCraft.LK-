package com.example.handycraftlk.adaptors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handycraftlk.R
import com.example.handycraftlk.models.Order

class CompleteOrderAdapter(private  val orderList:ArrayList<Order> ): RecyclerView.Adapter <CompleteOrderAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.completeorderitemcus,
            parent,false)
        return MyViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var v="Refno"
        val currentItem= orderList[position]
        holder.itemName.text=currentItem.itemName
        holder.quantity.text=currentItem.quantity
        holder.dt.text=currentItem.dt
        holder.orderId.text=v+currentItem.orderId
        //holder.totalAmount.text=currentItem.totalAmount
    }

    override fun getItemCount(): Int {
        return orderList.size
    }



    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val itemName:TextView=itemView.findViewById(R.id.pname)
        val quantity:TextView=itemView.findViewById(R.id.pquantity)
        val dt:TextView=itemView.findViewById(R.id.dateTime)
        val orderId:TextView=itemView.findViewById(R.id.orderId)
        //val totalAmount:TextView=itemView.findViewById(R.id.totalPrice)

    }

}