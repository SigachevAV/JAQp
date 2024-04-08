package com.example.JAQpApi.Service;

import com.example.JAQpApi.Entity.ImageMetadata;
import com.example.JAQpApi.Entity.User.User;
import com.example.JAQpApi.Exeptions.*;
import com.example.JAQpApi.Repository.ImageMetadataRepo;
import io.minio.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService
{
    private final MinioClient minioClient;
    private final String bucketName = "jaqp-image";

    private final AuthService authService;
    private final ImageMetadataRepo imageMetadataRepo;

    public ImageMetadata GetImageMetadata(String _name) throws NotFoundException
    {
        return imageMetadataRepo.findById(_name).orElseThrow(()->new NotFoundException(""));
    }

    public byte[] LoadImage(String _filename) throws ImageStorageException
    {
        byte[] file;
        try
        {
            file = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(_filename).build()).readAllBytes();
        }
        catch (Exception e)
        {
            throw new ImageStorageException("Storage Access Error");
        }
        return file;
    }

    public void DeleteImage(String _filename, String _token) throws ImageException, UserExeption
    {
        ImageMetadata image = imageMetadataRepo.findById(_filename).orElseThrow(() -> new ImageNotFoundException("Image not found"));
        User author = authService.GetUserByToken(_token);
        if (!image.getUser().getId().equals(author.getId()))
        {
            throw new UserAccessDeniedExeption("");
        }
        try
        {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(image.getName()).build());
        }
        catch (Exception e)
        {
            throw new ImageStorageException("");
        }
        try
        {
            imageMetadataRepo.delete(image);
        }
        catch (Exception e)
        {
            throw new ImageException("Изображение не найдено");
        }
    }

    public static MediaType GetType(String _filename) throws ImageInvalidException
    {
        String extention = _filename.substring(_filename.lastIndexOf(".")+1);
        switch (extention)
        {
            case "png":
            {
                return MediaType.IMAGE_PNG;
            }
            case "jpeg":
            {
                return MediaType.IMAGE_JPEG;
            }
            case "gif":
            {
                return MediaType.IMAGE_GIF;
            }
            default:
            {
                throw new ImageInvalidException("invalid format");
            }
        }
    }

    public String UploadFile(MultipartFile _file, String _token) throws ImageException, UserNotFoundExeption
    {
        createBucket();
        if(_file.isEmpty() || _file.getOriginalFilename() == null)
        {
            throw new ImageInvalidException("Invalid file");
        }
        String fileName = generateFileName(_file);
        saveImage(_file, fileName);
        imageMetadataRepo.save(ImageMetadata.builder().name(fileName).user(authService.GetUserByToken(_token)).build());
        return fileName;
    }

    private void createBucket() throws ImageStorageException
    {
        try
        {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());
            if (!found)
            {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
            }
        }
        catch (Exception e)
        {
            throw new ImageStorageException("Storage Access Error");
        }
    }

    private String generateFileName(final MultipartFile _file)
    {
        String extension = getExtension(_file);
        return UUID.randomUUID() + "." + extension;
    }

    private String getExtension(final MultipartFile _file)
    {
        return _file.getOriginalFilename()
                .substring(_file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    private void saveImage(final MultipartFile _file, final String fileName) throws ImageException
    {
        try
        {
            InputStream inputStream = _file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .stream(inputStream, inputStream.available(), -1)
                    .bucket(bucketName)
                    .object(fileName)
                    .build());
        }
        catch (IOException e)
        {
            throw new ImageException("Unknown error");
        }
        catch (Exception e)
        {
            throw new ImageStorageException("Storage Access Error");
        }
    }
}
