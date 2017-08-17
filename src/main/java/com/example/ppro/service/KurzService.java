package com.example.ppro.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppro.model.Kurz;
import com.example.ppro.repository.KurzRepository;

@Service
public class KurzService {

	@Autowired
	private KurzRepository kurzRepository;
	
	public Collection<Kurz> findAllKurzy(){
		List<Kurz> kurzy = new ArrayList<Kurz>();
		for (Kurz Kurz : kurzRepository.findAll()) {
			kurzy.add(Kurz);
		}
		return kurzy;
	}
	
	public void deleteKurz(long id) {
		kurzRepository.delete(id);
	}
}
