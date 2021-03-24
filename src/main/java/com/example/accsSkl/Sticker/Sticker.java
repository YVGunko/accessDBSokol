package com.example.accsSkl.Sticker;

public class Sticker {

	public String getDateOfTrace() {
		return dateOfTrace;
	}
	public void setDateOfTrace(String dateOfTrace) {
		this.dateOfTrace = dateOfTrace;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSizing_row() {
		return sizing_row;
	}
	public void setSizing_row(String sizing_row) {
		this.sizing_row = sizing_row;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Long getNumberOfOrder() {
		return numberOfOrder;
	}
	public void setNumberOfOrder(Long numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}
	public String getSizing() {
		return sizing;
	}
	public void setSizing(String sizing) {
		this.sizing = sizing;
	}
	public Long getIdDoc() {
		return idDoc;
	}
	public void setIdDoc(Long idDoc) {
		this.idDoc = idDoc;
	}
	public Long getIdSticker() {
		return idSticker;
	}
	public void setIdSticker(Long idSticker) {
		this.idSticker = idSticker;
	}
    
	public Long getIdQRCode() {
		return idQRCode;
	}
	public void setIdQRCode(Long idQRCode) {
		this.idQRCode = idQRCode;
	}
public Sticker(String dateOfTrace, int quantity, String model, String sizing_row, String client, Long numberOfOrder,
			String sizing, Long idDoc, Long idSticker, Long idQRCode) {
		super();
		this.dateOfTrace = dateOfTrace;
		this.quantity = quantity;
		this.model = model;
		this.sizing_row = sizing_row;
		this.client = client;
		this.numberOfOrder = numberOfOrder;
		this.sizing = sizing;
		this.idDoc = idDoc;
		this.idSticker = idSticker;
		this.idQRCode = idQRCode;
	}
	/*SELECT c.`Дата` as dateOfTrace, c.`Пар_Коробе` as quantity, m.`№Модели` as model, r.`РазмерныйРяд` as sizing_row, 
 * cl.Получатель as client, d.[№Партии] as numberOfOrder, c.`Ростовка` sizing, c.кодДок AS idDoc, 
 * c.кодВыборКарт as idSticker, d.кодДвижЗакТабл as idQRCode
FROM `ВыборкаКарточек` AS c, `Движение_заказаТабл` AS d, ДВИЖЕНИЕ_ЗАКАЗА AS z,
КЛИЕНТ_ДОСТАВКА AS cl, МОДЕЛИ m, РОСТОВКИ r
WHERE c.кодДок=d.`кодДвижЗакТабл` AND d.кодДвижЗакТабл=1342
AND z.кодДвижЗаказа = d.кодДвижЗак 
AND z.Получатель = cl.кодКлиентДоставка
AND c.`кодМодель`= m.`кодМодели`
AND c.`Ростовка`=r.`кодРостовки`
ORDER BY idDoc, idSticker*/
    private String dateOfTrace; //Время
	private int quantity;
    private String model ; //№Модели
    private String sizing_row ; //Участок от
    private String client ; //Участок от
    private Long numberOfOrder; //№Партии
    private String sizing; //Разм/ряд
    private Long idDoc ; //OrderTrace
    private Long idSticker;
    private Long idQRCode; //OrderTraceDetail

    
}
