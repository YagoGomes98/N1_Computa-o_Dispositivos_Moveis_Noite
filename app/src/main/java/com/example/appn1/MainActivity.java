package com.example.appn1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvCadastros;
    private ArrayAdapter adapter;
    private List<Cadastro> listaDeCadastros;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCadastros = findViewById(R.id.lvCadastros);

        carregarCadastros();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvCadastros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idCadastro = listaDeCadastros.get( position ).getId();
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idCadastro" , idCadastro);
                startActivity(intent);
            }
        });

        lvCadastros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                excluir(position);
                return true;
            }
        });

    }

    private void excluir(int posicao){
        Cadastro cadastro = listaDeCadastros.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir...");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Confirma a exclusão do personagem " + cadastro.getNome() +"?");
        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CadastroDAO.excluir(MainActivity.this, cadastro.getId());
                carregarCadastros();
            }
        });
        alerta.show();
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        carregarCadastros();
    }



    private void carregarCadastros(){

        listaDeCadastros = CadastroDAO.getCadastros(this);
        if( listaDeCadastros.size() == 0 ) {
            Cadastro fake = new Cadastro("Lista Vazia...", "");
            listaDeCadastros.add(fake);
            lvCadastros.setEnabled(false);
        }else{
            lvCadastros.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeCadastros);
        lvCadastros.setAdapter(adapter);
    }



}