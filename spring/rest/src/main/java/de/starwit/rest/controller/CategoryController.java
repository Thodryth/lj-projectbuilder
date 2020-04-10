package de.starwit.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.starwit.persistence.entity.CategoryEntity;
import de.starwit.persistence.response.EntityListResponse;
import de.starwit.persistence.response.EntityResponse;
import de.starwit.persistence.response.ResponseCode;
import de.starwit.persistence.response.ResponseMetadata;
import de.starwit.persistence.validation.EntityValidator;
import de.starwit.service.impl.CategoryService;

/**
 * Domain RestController Have a look at the RequestMapping!!!!!!
 */
@RestController
@RequestMapping("${rest.base-path}/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/query/all")
  public EntityListResponse<CategoryEntity> findAll() {
    List<CategoryEntity> entities = this.categoryService.findAll();
    EntityListResponse<CategoryEntity> response = new EntityListResponse<CategoryEntity>(entities);
    ResponseMetadata responseMetadata = EntityValidator.isNotEmpty(response.getResult());
    response.setMetadata(responseMetadata);
    return response;
  }

  @GetMapping(value = "/query/{id}")
  public EntityResponse<CategoryEntity> findById(@PathVariable("id") Long id) {
    CategoryEntity entity = this.categoryService.findById(id);
    EntityResponse<CategoryEntity> rw = new EntityResponse<CategoryEntity>(entity);
    if (entity == null) {
      rw.setMetadata(new ResponseMetadata(ResponseCode.NOT_FOUND, "response.notfound"));
    }
    return rw;
  }

  @PutMapping
  public EntityResponse<CategoryEntity> save(@RequestBody CategoryEntity category) {
    EntityResponse<CategoryEntity> response = new EntityResponse<CategoryEntity>();
    response.setResult(this.categoryService.saveOrUpdate(category));
    return response;
  }

  @PostMapping
  public EntityResponse<CategoryEntity> update(@RequestBody CategoryEntity category) {
    EntityResponse<CategoryEntity> response = new EntityResponse<CategoryEntity>();
    response.setResult(this.categoryService.saveOrUpdate(category));
    return response;
  }

  @DeleteMapping(value = "/{id}")
  public EntityResponse<CategoryEntity> delete(@PathVariable("id") Long id) {
    CategoryEntity toBeDeleted = this.categoryService.findById(id);
    this.categoryService.delete(toBeDeleted);

    ResponseMetadata responseMetadata = new ResponseMetadata();
    responseMetadata.setResponseCode(ResponseCode.OK);
    responseMetadata.setMessage("Der Eintrag wurde gelöscht.");

    EntityResponse<CategoryEntity> response = new EntityResponse<CategoryEntity>();
    response.setMetadata(responseMetadata);

    return response;
  }

}
