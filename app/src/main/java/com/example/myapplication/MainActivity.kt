package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sh = getSharedPreferences("listadeprodutos", Context.MODE_PRIVATE)

        btnLucroPrejuizo.setOnClickListener { view: View? ->

            var precoCusto = txtCustoProduto.text.toString()
            var precoVenda = txtVendaProduto.text.toString()

            if(precoCusto.isNotEmpty() && precoVenda.isNotEmpty()){

                var razao = precoCusto.toFloat()-precoVenda.toFloat()

                if(razao<0){
                    var razaopositiva = razao*(-1)
                    Toast.makeText(this,"Houve um lucro de R$"+razaopositiva,Toast.LENGTH_SHORT).show()
                }else if(razao>0){
                    Toast.makeText(this,"Houve um Prejuízo de R$"+razao,Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Não houve lucro nem prejuízo",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this,"Os campos precisam ser preenchidos!",Toast.LENGTH_SHORT).show()
            }
        }

        btnAdicionar.setOnClickListener { view: View? ->

            if(txtProduto.text.isNotEmpty()){
                sh.edit().putString(txtNomeAnotacao.text.toString(),txtProduto.text.toString()).apply()
                Toast.makeText(this,"Produto salvo na lista!",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Anotação inexistente",Toast.LENGTH_SHORT).show()
            }
        }

        btnAbrir.setOnClickListener { view: View? ->
            if(txtProduto.text.isNotEmpty()){

                var texto = sh.getString(txtNomeAnotacao.text.toString(),"")
                if(texto.isNullOrEmpty()){
                    Toast.makeText(this,"Anotação vazia ou inexistente!",Toast.LENGTH_SHORT).show()
                }
                else{
                    txtProduto.setText(texto)
                    Toast.makeText(this,"Anotação carregada com sucesso!",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this,"Anotação inexistente",Toast.LENGTH_SHORT).show()
            }
        }

        btnLimpar.setOnClickListener { view: View? ->

            txtProduto.text.clear()
            txtNomeAnotacao.text.clear()

        }
    }
}
