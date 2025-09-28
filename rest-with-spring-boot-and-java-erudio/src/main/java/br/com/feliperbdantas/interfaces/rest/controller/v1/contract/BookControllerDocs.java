package br.com.feliperbdantas.interfaces.rest.controller.v1.contract;

import br.com.feliperbdantas.application.dto.v1.BookDTO;
import br.com.feliperbdantas.application.dto.v1.wrapper.BooksWrapperDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Book", description = "Endpoints for Managing Book")
public interface BookControllerDocs {
    @Operation(
            summary = "Find All Book",
            description = "Find All Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                                    ),
                                    @Content(
                                            mediaType = MediaType.APPLICATION_XML_VALUE,
                                            schema = @Schema(implementation = BooksWrapperDTO.class)
                                    ),
                                    @Content(
                                            mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<BooksWrapperDTO> findAll();

    @Operation(
            summary = "Find a Book",
            description = "Find a specific book by your ID",
            tags = {"Book"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = BookDTO.class)
                                    ),
                                    @Content(
                                            mediaType = MediaType.APPLICATION_XML_VALUE,
                                            schema = @Schema(implementation = BookDTO.class)
                                    ),
                                    @Content(
                                            mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            schema = @Schema(implementation = BookDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id);

    @Operation(summary = "Adds a new Book",
            description = "Adds a new book by passing in a JSON, XML or YML representation of the book.",
            tags = {"Book"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO book);

    @Operation(summary = "Updates a book's information",
            description = "Updates a book's information by passing in a JSON, XML or YML representation of the updated book.",
            tags = {"Book"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO book);

    @Operation(summary = "Deletes a Book",
            description = "Deletes a specific book by their ID",
            tags = {"Book"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204", content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
