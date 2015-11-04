package br.com.diegodantas.model;

import android.content.ContentValues;
import android.database.Cursor;

import br.com.diegodantas.interfaces.ObjectModel;

public class Lancamento implements ObjectModel {

	private Long id;
	private int dia;
	private int mes;
	private int ano;
	private Float quantidadeCliente;
	private Float quantidadeKg;
	private Float precoKg;
	private Float receitaOutrosProdutos;
	private Float gastoKg;
	private Float gastoOutrosProdutos;
	private Float gastoOperacional;
	private Float valorDinheiro;
	private Float valorCredito;
	private Float valorDebito;

	@Override
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();

		values.put("id", this.getId());
		values.put("dia", this.getDia());
		values.put("mes", this.getMes());
		values.put("ano", this.getAno());
		values.put("quantidade_cliente", this.getQuantidadeCliente());
		values.put("quantidade_kg", this.getQuantidadeKg());
		values.put("preco_kg", this.getPrecoKg());
		values.put("receita_outros_produtos", this.getReceitaOutrosProdutos());
		values.put("gasto_kg", this.getGastoKg());
		values.put("gasto_outros_produtos", this.getGastoOutrosProdutos());
		values.put("gasto_operacional", this.getGastoOperacional());
		values.put("valor_dinheiro", this.getValorDinheiro());
		values.put("valor_credito", this.getValorCredito());
		values.put("valor_debito", this.getValorDebito());

		return values;
	}

	@Override
	public ObjectModel cursorToObjectModel(Cursor cursor) {
		Lancamento obj = new Lancamento();

		if (cursor.getCount() == 0)
			return obj;

		cursor.moveToFirst();

		obj.setId(cursor.getLong(cursor.getColumnIndex("id")));
		obj.setDia(cursor.getInt(cursor.getColumnIndex("dia")));
		obj.setMes(cursor.getInt(cursor.getColumnIndex("mes")));
		obj.setAno(cursor.getInt(cursor.getColumnIndex("ano")));
		obj.setQuantidadeCliente(cursor.getFloat(cursor.getColumnIndex("quantidade_cliente")));
		obj.setQuantidadeKg(cursor.getFloat(cursor.getColumnIndex("quantidade_kg")));
		obj.setPrecoKg(cursor.getFloat(cursor.getColumnIndex("preco_kg")));
		obj.setReceitaOutrosProdutos(cursor.getFloat(cursor.getColumnIndex("receita_outros_produtos")));
		obj.setGastoKg(cursor.getFloat(cursor.getColumnIndex("gasto_kg")));
		obj.setGastoOutrosProdutos(cursor.getFloat(cursor.getColumnIndex("gasto_outros_produtos")));
		obj.setGastoOperacional(cursor.getFloat(cursor.getColumnIndex("gasto_operacional")));
		obj.setValorDinheiro(cursor.getFloat(cursor.getColumnIndex("valor_dinheiro")));
		obj.setValorCredito(cursor.getFloat(cursor.getColumnIndex("valor_credito")));
		obj.setValorDebito(cursor.getFloat(cursor.getColumnIndex("valor_debito")));

		return obj;
	}

	@Override
	public void validaCamposObrigatorios() throws Exception {
		if (this.getDia() == 0)
			throw new Exception("O campo 'Dia' é obrigatório!");
		else if (this.getMes() == 0)
			throw new Exception("O campo 'Mês' é obrigatório!");
		else if (this.getAno() == 0)
			throw new Exception("O campo 'Ano' é obrigatório!");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Float getQuantidadeCliente() {
		return quantidadeCliente;
	}

	public void setQuantidadeCliente(Float quantidadeCliente) {
		this.quantidadeCliente = quantidadeCliente;
	}

	public Float getQuantidadeKg() {
		return quantidadeKg;
	}

	public void setQuantidadeKg(Float quantidadeKg) {
		this.quantidadeKg = quantidadeKg;
	}

	public Float getPrecoKg() {
		return precoKg;
	}

	public void setPrecoKg(Float precoKg) {
		this.precoKg = precoKg;
	}

	public Float getReceitaOutrosProdutos() {
		return receitaOutrosProdutos;
	}

	public void setReceitaOutrosProdutos(Float receitaOutrosProdutos) {
		this.receitaOutrosProdutos = receitaOutrosProdutos;
	}

	public Float getGastoKg() {
		return gastoKg;
	}

	public void setGastoKg(Float gastoKg) {
		this.gastoKg = gastoKg;
	}

	public Float getGastoOutrosProdutos() {
		return gastoOutrosProdutos;
	}

	public void setGastoOutrosProdutos(Float gastoOutrosProdutos) {
		this.gastoOutrosProdutos = gastoOutrosProdutos;
	}

	public Float getGastoOperacional() {
		return gastoOperacional;
	}

	public void setGastoOperacional(Float gastoOperacional) {
		this.gastoOperacional = gastoOperacional;
	}

	public Float getValorDinheiro() {
		return valorDinheiro;
	}

	public void setValorDinheiro(Float valorDinheiro) {
		this.valorDinheiro = valorDinheiro;
	}

	public Float getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(Float valorCredito) {
		this.valorCredito = valorCredito;
	}

	public Float getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(Float valorDebito) {
		this.valorDebito = valorDebito;
	}
}