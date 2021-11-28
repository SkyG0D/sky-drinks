package com.github.skyg0d.skydrinksapi.controller;

import com.github.skyg0d.skydrinksapi.domain.Table;
import com.github.skyg0d.skydrinksapi.parameters.TableParameters;
import com.github.skyg0d.skydrinksapi.requests.TablePostRequestBody;
import com.github.skyg0d.skydrinksapi.requests.TablePutRequestBody;
import com.github.skyg0d.skydrinksapi.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    @GetMapping("/waiter")
    public ResponseEntity<Page<Table>> listAll(Pageable pageable) {
        return ResponseEntity.ok(tableService.listAll(pageable));
    }

    @GetMapping("/waiter/{uuid}")
    public ResponseEntity<Table> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(tableService.findByIdOrElseThrowBadRequestException(uuid));
    }

    @GetMapping("/waiter/search")
    public ResponseEntity<Page<Table>> search(TableParameters tableParameters, Pageable pageable) {
        return ResponseEntity.ok(tableService.search(tableParameters, pageable));
    }

    @GetMapping("/waiter/find-by-number/{number}")
    public ResponseEntity<Table> findByNumber(@PathVariable int number) {
        return ResponseEntity.ok(tableService.findByNumberOrElseThrowBadRequestException(number));
    }

    @PostMapping("/waiter")
    public ResponseEntity<Table> save(@RequestBody @Valid TablePostRequestBody tablePostRequestBody) {
        return new ResponseEntity<>(tableService.save(tablePostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/waiter")
    public ResponseEntity<Void> replace(@RequestBody @Valid TablePutRequestBody tablePutRequestBody) {
        tableService.replace(tablePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/waiter/switch-occupied/{identification}")
    public ResponseEntity<Table> switchOccupied(@PathVariable String identification) {
        return ResponseEntity.ok(tableService.switchOccupied(identification));
    }

    @DeleteMapping("/waiter/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        tableService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
