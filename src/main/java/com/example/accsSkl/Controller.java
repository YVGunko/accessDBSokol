package com.example.accsSkl;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.accsSkl.Client.ClientRepository;
import com.example.accsSkl.Box.Box;
import com.example.accsSkl.Box.BoxRepository;
import com.example.accsSkl.Brand.Brand;
import com.example.accsSkl.Brand.BrandRepository;
import com.example.accsSkl.Client.Client;
import com.example.accsSkl.Contragent.ContragentRepository;
import com.example.accsSkl.Model.Model;
import com.example.accsSkl.Model.ModelRepository;
import com.example.accsSkl.OrderTrace.OrderTrace;
import com.example.accsSkl.OrderTrace.OrderTraceRepository;
import com.example.accsSkl.OrderTraceDetail.OrderTraceDetail;
import com.example.accsSkl.OrderTraceDetail.OrderTraceDetailRepository;
import com.example.accsSkl.OrderTraceWithDetails.OrderTraceWithDetails;
import com.example.accsSkl.Season.Season;
import com.example.accsSkl.Season.SeasonRepository;
import com.example.accsSkl.Sizing.Sizing;
import com.example.accsSkl.Sizing.SizingRepository;
import com.example.accsSkl.Sticker.Sticker;
import com.example.accsSkl.Sticker.StickerRepository;
import com.example.accsSkl.Contragent.Contragent;

@RestController
public class Controller {
	@RequestMapping(path="/checkConnection", method=RequestMethod.GET)
	public String checkConnection(){
		return "Ok";
	}
	@Autowired
	ClientRepository clientRepository;
	@GetMapping("/getAllClient") 
	public List<Client> getAllClients() throws RuntimeException{
			return clientRepository.findAll();
	}
	
	@GetMapping("/getOneClient") 
	public Client getOneClient(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return clientRepository.findOne(name);
	}

	@Autowired
	ContragentRepository contragentRepository;
	@GetMapping("/getAllContragent") 
	public List<Contragent> getAllContragent() throws RuntimeException{
			return contragentRepository.findAll();
	}
	
	@GetMapping("/getOneContragent") 
	public Contragent getOneContragent(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return contragentRepository.findOne(name);
	}
	
	@Autowired
	ModelRepository modelRepository;
	@GetMapping("/getAllModel") 
	public List<Model> getAllModel() throws RuntimeException{
			return modelRepository.findAll();
	}
	@GetMapping("/getOneModel") 
	public Model getOneModel(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return modelRepository.findOne(name);
	}
	
	@Autowired
	BrandRepository brandRepository;
	@GetMapping("/getAllBrand") 
	public List<Brand> getAllBrand() throws RuntimeException{
			return brandRepository.findAll();
	}
	@GetMapping("/getOneBrand") 
	public Brand getOneBrand(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return brandRepository.findOne(name);
	}
	
	@Autowired
	SeasonRepository seasonRepository;
	@GetMapping("/getAllSeason") 
	public List<Season> getAllSeason() throws RuntimeException{
			return seasonRepository.findAll();
	}
	@GetMapping("/getOneSeason") 
	public Season getOneSeason(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return seasonRepository.findOne(name);
	}
	
	@Autowired
	SizingRepository sizingRepository;
	@GetMapping("/getAllSizing") 
	public List<Sizing> getAllSizing() throws RuntimeException{
			return sizingRepository.findAll();
	}
	@GetMapping("/getOneSizing") 
	public Sizing getOneSizing(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return sizingRepository.findOne(name);
	}
	
	@Autowired
	OrderTraceRepository orderTraceRepository;
	@GetMapping("/getAllOrderTrace") 
	public List<OrderTrace> getAllOrderTrace() throws RuntimeException{
			return orderTraceRepository.findAll();
	}
	@GetMapping("/getOneOrderTrace") 
	public OrderTrace getOneOrderTrace(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return orderTraceRepository.findOne(name); 
	}
	@GetMapping("/getLaunchOrderTrace") 
	public List<OrderTrace> getLaunchOrderTrace(@RequestParam(value="date", required=true) long date) throws RuntimeException{
		return orderTraceRepository.findAllGreaterThan(date); 
	}
	@PostMapping("/insertOneOrderTrace") 
	public Long insertOneOrderTrace(@RequestBody @Valid OrderTrace orderTrace) throws Exception{
		if (orderTrace==null)
			return (long)0;
		else
			return orderTraceRepository.insert(orderTrace);
	}
	@PostMapping("/updateOneOrderTrace") 
	public int upateOneOrderTrace(@RequestBody @Valid OrderTrace orderTrace) throws Exception{
		if (orderTrace==null)
			return 0;
		else
			return orderTraceRepository.update(orderTrace);
	}
	@PostMapping("/deleteOneOrderTrace") 
	public int insertOneOrderTrace(@RequestBody @Valid Long id) throws Exception{
		if (id==null)
			return 0;
		else
			return orderTraceRepository.delete(id);
	}
	
	@Autowired
	OrderTraceDetailRepository orderTraceDetailRepository;
	@GetMapping("/getAllRowsOfDoc") 
	public List<OrderTraceDetail> getAllRowsOfDoc(@RequestParam(value="id", required=true) Long id) throws RuntimeException{
			return orderTraceDetailRepository.findAllRowsOfDoc(id);
	}
	@GetMapping("/getAllRowsAndDoc") 
	public List<OrderTraceDetail> getAllRowsAndDoc(@RequestParam(value="date", required=true) Long date) throws RuntimeException{
		System.out.println("/getAllRowsAndDoc. date = "+getStringDayTime(date)+". Time is "+ getStringDayTime(new Date().getTime()));
		return orderTraceDetailRepository.findAllRowsAndDoc(date);
	}
	@GetMapping("/getCards") 
	public List<OrderTraceDetail> getCards(@RequestParam(value="date", required=true) Long date) throws RuntimeException{
			return orderTraceDetailRepository.findAllRowsAndDoc(date);
	}
	@GetMapping("/getOneOrderTraceDetail") 
	public OrderTraceDetail getOneOrderTraceDetail(@RequestParam(value="name", required=true) String name) throws RuntimeException{
		if (name==null)
			return null;
		else
			return orderTraceDetailRepository.findOne(name); 
	}
	
	@GetMapping("/OrderTraceWithDetails") 
	public OrderTraceWithDetails getOrderTraceWithDetails(@RequestParam(value="date", required=true) Long date)  throws Exception{
		OrderTraceWithDetails responce = new OrderTraceWithDetails();

		long startSync = System.currentTimeMillis();	
		List<OrderTrace> savedOrderTraceList = orderTraceRepository.findAllGreaterThan(date);
		if (savedOrderTraceList!=null) {
			for (OrderTrace b : savedOrderTraceList) {
				responce.orderTrace.add(new OrderTrace(b.getId(), b.getNumberOfOrder(), b.getDateOfTrace(), b.getNameOfTrace(), 
						b.getSender().getId(), b.getReceiver().getId(), b.getClient().getId(), b.getBrand().getId(), b.getSeason().getId()));

				List<OrderTraceDetail> savedOrderTraceDetailList = orderTraceDetailRepository.findAllRowsOfDoc(b.getId());
				if (savedOrderTraceDetailList!=null) {
					for (OrderTraceDetail bm : savedOrderTraceDetailList) {
						responce.orderTraceDetail.add(new OrderTraceDetail(bm.getId(), b.getId(), bm.getNumberOfOrder(), 
								bm.getModel().getId(), bm.getSizing().getId(), bm.getQuantity()));
					}
				}
			}
		}
		System.out.println("OrderTraceWithDetails. Thats it! " + (System.currentTimeMillis() - startSync));

		return responce;
	}

	@Autowired
	StickerRepository stickerRepository;
    @RequestMapping(value = "/pdfreport", params="date", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> stickerReport(@RequestParam(value = "date") Long date) throws IOException {

        List<Sticker> responce = (List<Sticker>) stickerRepository.getSticker();

        ByteArrayInputStream bis = GeneratePdfReport.stickerReport(responce);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "inline; filename=stickers"+getStringDayTime(date)+".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	@GetMapping("/getStickerGreaterThan") 
	public List<Sticker> findStickerGreaterThan(@RequestParam(value="idStickerMax", required=true) Long idStickerMax) throws RuntimeException{
		System.out.println("/getStickerGreaterThan. idStickerMax = "+idStickerMax+". Time is "+ getStringDayTime(new Date().getTime()));
		return stickerRepository.findStickerGreaterThan(idStickerMax);
	}
	
		
	@PostMapping("/setBox") 
	@Transactional
	public List<Box> insertBox(@RequestBody @Valid List<Box> box) throws Exception{
		System.out.println("/setBox. Time is "+ getStringDayTime(new Date().getTime()));
		List<Box> responce = new ArrayList<>();
		Long parentId = (long)0;
		if (box==null)	return box;
		else {
			System.out.println("Box row count " + String.valueOf(box.size())+" now is "+ getStringDayTime(new Date().getTime()));
			for (Box b : box) {
				System.out.println("Box id is " + String.valueOf(b.getId())+". now is "+ getStringDayTime(new Date().getTime()));
				/*public OrderTrace (Long id, Long numberOfOrder, Long dateOfTrace, String nameOfTrace, 
			Long sender_id,	Long receiver_id, Long client_id, Long brand_id, Long season_id)*/
				Long ldate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(b.getDateOfTrace()).getTime();
				String sdate = getStringDate(ldate);

				//Проверяем есть ли уже документ с указанными параметрами 
				parentId = orderTraceRepository.findOne(b.getNumberOfOrder(), sdate,
						 b.getSender_id(), b.getReceiver_id(), b.getClient_id(), b.getBrand_id(), b.getSeason_id()).getId();
				System.out.println("parentId = orderTraceRepository " + String.valueOf(parentId)+" now is "+ getStringDayTime(new Date().getTime()));
				if (parentId == 0) {//Нету, добавляем документ и получаем его первичный ключ для записи в ссылку в поле строк
						OrderTrace orderTrace = new OrderTrace ((long)1, b.getNumberOfOrder(), ldate, b.snameOfTrace, b.getSender_id(),
						b.getReceiver_id(), b.getClient_id(), b.getBrand_id(), b.getSeason_id());
						parentId = orderTraceRepository.insert(orderTrace);
						System.out.println("parentId = orderTraceRepository.insert(orderTrace) " + String.valueOf(parentId)+" now is "+ getStringDayTime(new Date().getTime()));
						}
				if (parentId > 0) {// если есть код ссылки добавляем строку документа со ссылкой на этот документ
					/*public OrderTraceDetail (Long id, Long parent_id, Long numberOfOrder,	Long model_id, Long sizing_id, int quantity)*/
					OrderTraceDetail orderTraceDetail = new OrderTraceDetail((long)1, parentId, b.getNumberOfOrder(), 
							b.getModel_id(), b.getSizing_id(), b.getQuantity());
					if (orderTraceDetailRepository.insert(orderTraceDetail)>0) {
						responce.add(b);
						System.out.println("responce.add(b) " + String.valueOf(parentId)+" now is "+ getStringDayTime(new Date().getTime()));
					}
				}
			}		
			return responce;
		}
			
	}
	
	
    private String getStringDayTime(Long date) {
        return org.apache.commons.lang3.time.DateFormatUtils.format(date, "dd.MM.yyyy HH:mm:ss");
    }
    private String getStringDate(Long date) {
        return org.apache.commons.lang3.time.DateFormatUtils.format(date, "yyyy-MM-dd");
    }
}
