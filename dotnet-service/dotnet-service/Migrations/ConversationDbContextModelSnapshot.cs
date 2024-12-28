﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using dotnet_service.Data;

#nullable disable

namespace dotnet_service.Migrations
{
    [DbContext(typeof(ConversationDbContext))]
    partial class ConversationDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "8.0.11")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            MySqlModelBuilderExtensions.AutoIncrementColumns(modelBuilder);

            modelBuilder.Entity("dotnet_service.Entities.Conversation", b =>
                {
                    b.Property<string>("Name")
                        .HasColumnType("varchar(255)");

                    b.Property<DateTime>("BeganAt")
                        .HasColumnType("datetime(6)");

                    b.HasKey("Name");

                    b.ToTable("Conversations");
                });

            modelBuilder.Entity("dotnet_service.Entities.Message", b =>
                {
                    b.Property<string>("ConversationName")
                        .HasColumnType("varchar(255)");

                    b.Property<long>("MessageId")
                        .HasColumnType("bigint");

                    b.Property<int>("Id")
                        .HasColumnType("int");

                    b.Property<string>("TimestampReceived")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("TimestampSent")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.HasKey("ConversationName", "MessageId");

                    b.ToTable("Messages");
                });

            modelBuilder.Entity("dotnet_service.Entities.Message", b =>
                {
                    b.HasOne("dotnet_service.Entities.Conversation", "Conversation")
                        .WithMany("Messages")
                        .HasForeignKey("ConversationName")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Conversation");
                });

            modelBuilder.Entity("dotnet_service.Entities.Conversation", b =>
                {
                    b.Navigation("Messages");
                });
#pragma warning restore 612, 618
        }
    }
}
