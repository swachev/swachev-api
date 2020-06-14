package com.evliion.ev.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evliion.ev.payload.UpLoadFilesRequest;
import com.evliion.ev.payload.UploadFileResponse;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.service.FileStorageService;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);
	@Autowired
	FileStorageService fileStorageService;

	@PostMapping(value = "/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestBody UpLoadFilesRequest request) {
		String fileName = fileStorageService.storeFile(file, request.getCountryName(), request.getDocumentType());
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
		
	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam("docType") String docType, HttpServletRequest request) {
		String fileName = fileStorageService.getDocumentName(docType);
		Resource resource = null;
		if (fileName != null && !fileName.isEmpty()) {
			try {
				resource = fileStorageService.loadFileAsResource(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException ex) {
				logger.info("Could not determine file type." + ex.getMessage());
			}
			if (contentType == null) {
				contentType = "application/octet-stream";
			}
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
//	@GetMapping("/downloadFile/{fileName}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
//        // Load file as Resource
//        Resource resource = null;
//		try {
//			resource = fileStorageService.loadFileAsResource(fileName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }

}
